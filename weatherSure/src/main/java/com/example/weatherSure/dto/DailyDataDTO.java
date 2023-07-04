package com.example.weatherSure.dto;

public class DailyDataDTO {

  private String date;
  private String maxTempF;
  private String minTempF;
  private String condition;
  private String icon;


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
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


}
