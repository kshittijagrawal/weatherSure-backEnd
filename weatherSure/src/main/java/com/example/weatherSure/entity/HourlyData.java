package com.example.weatherSure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "hourly")
public class HourlyData implements Serializable {

  private String time;
  private String tempC;
  private String tempF;
  private String visibility;
  private String humidity;

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getTempC() {
    return tempC;
  }

  public void setTempC(String tempC) {
    this.tempC = tempC;
  }

  public String getTempF() {
    return tempF;
  }

  public void setTempF(String tempF) {
    this.tempF = tempF;
  }

  public String getVisibility() {
    return visibility;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  public String getHumidity() {
    return humidity;
  }

  public void setHumidity(String humidity) {
    this.humidity = humidity;
  }
}
