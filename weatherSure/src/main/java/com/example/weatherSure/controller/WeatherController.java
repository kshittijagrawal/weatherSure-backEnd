package com.example.weatherSure.controller;

import com.example.weatherSure.dto.CurrentDataDTO;
import com.example.weatherSure.dto.DailyDataDTO;
import com.example.weatherSure.dto.HourlyDataDTO;
import com.example.weatherSure.dto.LocationDTO;
import com.example.weatherSure.entity.Location;
import com.example.weatherSure.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/print")
    public ResponseEntity<String> print() {
        return new ResponseEntity<>("hello from the weather backend", HttpStatus.OK);
    }

    @PostMapping("/admin/location")
    public ResponseEntity<String> addLocationAndDetails(@RequestBody LocationDTO locationDTO) {
        String locationAdded = weatherService.addLocationAndDetails(locationDTO);
        return new ResponseEntity<>(locationAdded + " added to the database", HttpStatus.OK);

    }

    @GetMapping("/admin/details/{queryLocation}")
    public ResponseEntity<Location> getDetails(@PathVariable("queryLocation") String queryLocation)throws Exception {
        return new ResponseEntity<>(weatherService.getDetails(queryLocation), HttpStatus.OK);
    }

    @DeleteMapping("/admin/location/{queryLocation}")
    public ResponseEntity<String> deleteLocation(@PathVariable("queryLocation") String queryLocation) {
        Boolean res = weatherService.deleteLocation(queryLocation);
        if (res)
            return new ResponseEntity<>("deleted " + queryLocation + "'s data", HttpStatus.OK);
        else
            return new ResponseEntity<>(queryLocation + " not present in the data", HttpStatus.OK);
    }

    @DeleteMapping("/admin/completeRedisData")
    public ResponseEntity<String> deleteRedisData(){
        Boolean res = weatherService.deleteRedisData();
        if (res)
            return new ResponseEntity<>("successfully cleared cache.", HttpStatus.OK);
        else
            return new ResponseEntity<>("system couldn't clear cache.", HttpStatus.OK);
    }

    @PostMapping("/admin/currentData/{queryLocation}")
    public ResponseEntity<String> updateCurrentData(@PathVariable("queryLocation") String queryLocation, @RequestBody CurrentDataDTO currentDataDTO)throws Exception {
        Boolean res = weatherService.updateCurrentData(queryLocation, currentDataDTO);
        if (res)
            return new ResponseEntity<>("details for " + queryLocation + " updated", HttpStatus.OK);
        else
            return new ResponseEntity<>(queryLocation + " not present in the data", HttpStatus.OK);
    }

    @GetMapping("/redisDetails/{queryLocation}")
    public ResponseEntity<Location> getRedisDetails(@PathVariable String queryLocation) {
        return new ResponseEntity<>(weatherService.getRedisDetails(queryLocation), HttpStatus.OK);
    }

    @PostMapping("/admin/dailyData/{queryLocation}")
    public ResponseEntity<String> addOrUpdateDailyData(@PathVariable("queryLocation") String queryLocation, @RequestBody DailyDataDTO dailyDataDTO)throws Exception{
        Boolean res = weatherService.addOrUpdateDailyData(queryLocation, dailyDataDTO);
        if (res)
            return new ResponseEntity<>("details for " + queryLocation + " updated", HttpStatus.OK);
        else
            return new ResponseEntity<>(queryLocation + " not present in the data", HttpStatus.OK);
    }

    @PostMapping("/admin/hourlyData/{queryLocation}/{date}")
    public ResponseEntity<String> addOrUpdateHourlyData(@PathVariable("queryLocation") String queryLocation, @PathVariable("date") String date,
                                                        @RequestBody HourlyDataDTO hourlyDataDTO)throws Exception {
        Integer res = weatherService.addOrUpdateHourlyData(queryLocation, date, hourlyDataDTO);
        if (res == 1)
            return new ResponseEntity<>("details for " + queryLocation + " updated", HttpStatus.OK);
        else if (res == 0)
            return new ResponseEntity<>(queryLocation + " not present in the data", HttpStatus.OK);
        else
            return new ResponseEntity<>(date + " not present in " + queryLocation, HttpStatus.OK);
    }


}
