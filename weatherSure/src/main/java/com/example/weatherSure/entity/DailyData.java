package com.example.weatherSure.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Data
@ToString
@Document(collection = "daily")
public class DailyData implements Serializable {

  private String date;
  private String day;
  private String maxTempF;
  private String minTempF;
  private String condition;
  private String icon;
  private List<HourlyData> hourlyData;
  // hourlyData is a list of Hourly Data Objects containing "time", "tempC", "tempF", "humidity" and
  // "visibility" as attributes

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getMaxTempF() {
    return maxTempF;
  }

  public void setMaxTempF(String maxTempF) {
    this.maxTempF = maxTempF;
  }

  public String getMinTempF() {
    return minTempF;
  }

  public void setMinTempF(String minTempF) {
    this.minTempF = minTempF;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public List<HourlyData> getHourlyData() {
    return hourlyData;
  }

  public void setHourlyData(List<HourlyData> hourlyData) {
    this.hourlyData = hourlyData;
  }
}
