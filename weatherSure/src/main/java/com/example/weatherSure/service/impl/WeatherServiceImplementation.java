package com.example.weatherSure.service.impl;

import com.example.weatherSure.dto.CurrentDataDTO;
import com.example.weatherSure.dto.DailyDataDTO;
import com.example.weatherSure.dto.HourlyDataDTO;
import com.example.weatherSure.dto.LocationDTO;
import com.example.weatherSure.entity.CurrentData;
import com.example.weatherSure.entity.DailyData;
import com.example.weatherSure.entity.HourlyData;
import com.example.weatherSure.entity.Location;
import com.example.weatherSure.repository.CurrentDataRepository;
import com.example.weatherSure.repository.HourlyDataRepository;
import com.example.weatherSure.repository.LocationRepository;
import com.example.weatherSure.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WeatherServiceImplementation implements WeatherService {
  @Autowired
  LocationRepository locationRepository;

  @Autowired
  CurrentDataRepository currentDataRepository;

  @Autowired
  HourlyDataRepository hourlyDataRepository;

  @Autowired
  RedisTemplate redisTemplate;

  private static final String KEY = "location";

  @Override
  public String addLocationAndDetails(LocationDTO locationDTO) {
    Location location = new Location();
    BeanUtils.copyProperties(locationDTO, location);
    List<DailyData> dailyDataList = new ArrayList<>();

    for (DailyDataDTO dailyDataDTO : locationDTO.getDailyData()) {
      DailyData dailyData = new DailyData();
      BeanUtils.copyProperties(dailyDataDTO, dailyData);
      dailyData.setHourlyData(new ArrayList<>());
      dailyData.setDay(null);
      dailyDataList.add(dailyData);
    }

    location.setDailyData(dailyDataList);

    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    Date date = new Date();
    location.setDateTimeUpdated(formatter.format(date));

    locationRepository.save(location);
    redisTemplate.opsForHash().put(KEY, location.getName(), location);
    return location.getName();
  }

  @Override
  public Location getRedisDetails(String queryLocation) {
    ObjectMapper mapper = new ObjectMapper();
    Location location =
        mapper.convertValue(redisTemplate.opsForHash().get(KEY, queryLocation), Location.class);
    return location;
  }

  @Override
  public Optional<Location> findLocationById(String queryLocation) {
    return locationRepository.findById(queryLocation);
  }

  @Override
  public Boolean updateCurrentData(String queryLocation, CurrentDataDTO currentDataDTO)
      throws Exception {
    Optional<Location> location = findLocationById(queryLocation);
    try {
      CurrentData currentData = new CurrentData();
      BeanUtils.copyProperties(currentDataDTO, currentData);
      currentDataRepository.save(currentData);

      SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
      Date date = new Date();

      Location locationUpdated = location.get();
      locationUpdated.setCurrentData(currentData);
      locationUpdated.setDateTimeUpdated(formatter.format(date));

      locationRepository.save(locationUpdated);
      redisTemplate.opsForHash().put(KEY, locationUpdated.getName(), locationUpdated);

      return true;
    } catch (Exception ex) {
      System.out.println("Trying to access something that is not there! " + ex);
      ex.printStackTrace();
      return false;
    }
  }

  @Override
  public Boolean deleteLocation(String queryLocation) {
    Long res = redisTemplate.opsForHash().delete(KEY, queryLocation);
    System.out.println("Redis deleted: " + res + " field(s) from its data.");

    if (locationRepository.existsById(queryLocation)) {
      locationRepository.deleteById(queryLocation);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Boolean deleteRedisData() {
    Boolean res = redisTemplate.delete(KEY);
    return res;
  }

  @Override
  public Location getDetails(String queryLocation) throws Exception {
    Optional<Location> location = findLocationById(queryLocation);
    try {
      return location.get();
    } catch (Exception ex) {
      System.out.println("Trying to access something that is not there! " + ex);
      ex.printStackTrace();
      return null;
    }
  }

  @Override
  public Integer getTotalCount() {
    return locationRepository.findAll().size();
  }

  @Override
  public List<HashMap> getAllLocations() {
    List<HashMap> result = new ArrayList<>();
    List<Location> data = locationRepository.findAll();
    for (Location singleData : data) {
      HashMap<String, String> singleLocation = new HashMap<>();
      singleLocation.put("location", singleData.getName());
      singleLocation.put("dateTimeUpdated", singleData.getDateTimeUpdated());
      result.add(singleLocation);
    }
    return result;
  }

  @Override
  public Boolean addOrUpdateDailyData(String queryLocation, DailyDataDTO dailyDataDTO)
      throws Exception {
    Optional<Location> location = findLocationById(queryLocation);
    try {
      Boolean flag = false;
      String dateToCheck = dailyDataDTO.getDate();
      Location location1 = location.get();
      List<DailyData> dailyDataList = location1.getDailyData();
      for (DailyData dailyData : dailyDataList) {
        if (dailyData.getDate().equals(dateToCheck)) {
          flag = true;
          BeanUtils.copyProperties(dailyDataDTO, dailyData);
          locationRepository.save(location1);
        }
      }
      if (!flag) {
        DailyData dailyData = new DailyData();
        BeanUtils.copyProperties(dailyDataDTO, dailyData);
        dailyData.setDate(dateToCheck);
        dailyData.setHourlyData(new ArrayList<>());
        location1.getDailyData().add(dailyData);
        locationRepository.save(location1);
      }

      SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
      Date date = new Date();
      location1.setDateTimeUpdated(formatter.format(date));
      locationRepository.save(location1);
      redisTemplate.opsForHash().put(KEY, location1.getName(), location1);

      return true;

    } catch (Exception ex) {
      System.out.println("Trying to access something that is not there! " + ex);
      ex.printStackTrace();
      return false;
    }
  }

  @Override
  public Integer addOrUpdateHourlyData(String queryLocation, String date,
      HourlyDataDTO hourlyDataDTO) throws Exception {
    Optional<Location> location = locationRepository.findById(queryLocation);
    try {
      Location location1 = location.get();
      Boolean flag = false;
      for (DailyData dailyData : location1.getDailyData()) {
        if (dailyData.getDate().equals(date)) {
          flag = true;
          if (dailyData.getHourlyData().size() == 0) {
            List<HourlyData> hourlyDataList = new ArrayList<>();
            HourlyData hourlyData = new HourlyData();
            BeanUtils.copyProperties(hourlyDataDTO, hourlyData);
            hourlyDataList.add(hourlyData);
            dailyData.setHourlyData(hourlyDataList);
            locationRepository.save(location1);
            // HourlyData hourlyData = new HourlyData();
            // BeanUtils.copyProperties(hourlyDataDTO, hourlyData);
            // dailyData.getHourlyData().add(hourlyData);
            // locationRepository.save(location1);
          } else {
            HourlyData hourlyData = new HourlyData();
            BeanUtils.copyProperties(hourlyDataDTO, hourlyData);
            dailyData.getHourlyData().add(hourlyData);
            locationRepository.save(location1);
          }

          SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
          Date date1 = new Date();
          location1.setDateTimeUpdated(formatter.format(date1));
          locationRepository.save(location1);
          redisTemplate.opsForHash().put(KEY, location1.getName(), location1);

          break;
        }
      }
      if (flag)
        return 1;
      return 2;

    } catch (Exception ex) {
      System.out.println("Trying to access something that is not there! " + ex);
      ex.printStackTrace();
      return 0;
    }
  }
}
