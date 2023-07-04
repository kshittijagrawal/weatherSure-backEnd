package com.example.weatherSure.service;

import com.example.weatherSure.dto.CurrentDataDTO;
import com.example.weatherSure.dto.DailyDataDTO;
import com.example.weatherSure.dto.HourlyDataDTO;
import com.example.weatherSure.dto.LocationDTO;
import com.example.weatherSure.entity.Location;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface WeatherService {

  public String addLocationAndDetails(LocationDTO locationDTO);

  public Optional<Location> findLocationById(String queryLocation);

  public Boolean updateCurrentData(String queryLocation, CurrentDataDTO currentDataDTO)
      throws Exception;

  public Boolean deleteLocation(String queryLocation);

  public Boolean deleteAllLocations();

  public Boolean deleteRedisData();

  public Location getDetails(String queryLocation) throws Exception;

  public Integer getTotalCount();

  public List<HashMap> getAllLocations();

  public Location getRedisDetails(String queryLocation);

  public Boolean addOrUpdateDailyData(String queryLocation, DailyDataDTO dailyDataDTO)
      throws Exception;

  public Integer addOrUpdateHourlyData(String queryLocation, String date,
      HourlyDataDTO hourlyDataDTO) throws Exception;

}
