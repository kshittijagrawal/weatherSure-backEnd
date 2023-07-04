package com.example.weatherSure.dto;

import com.example.weatherSure.entity.CurrentData;
import com.example.weatherSure.entity.DailyData;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class LocationDTO {

  private String name;
  private String region;
  private CurrentData currentData;
  private List<DailyDataDTO> dailyData;

  public List<DailyDataDTO> getDailyData() {
    return dailyData;
  }

  public void setDailyData(List<DailyDataDTO> dailyData) {
    this.dailyData = dailyData;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public CurrentData getCurrentData() {
    return currentData;
  }

  public void setCurrentData(CurrentData currentData) {
    this.currentData = currentData;
  }


}
