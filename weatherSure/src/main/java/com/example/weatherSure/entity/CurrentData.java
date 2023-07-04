package com.example.weatherSure.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@ToString
@Document(collection = "current")
public class CurrentData implements Serializable {

  private String tempF;
  private String tempC;
  private String feelsLikeF;
  private String feelsLikeC;
  private String humidity;
  private String cloud;
  private String windSpeed;
  private String visibility;

  public String getTempF() {
    return tempF;
  }

  public void setTempF(String tempF) {
    this.tempF = tempF;
  }

  public String getTempC() {
    return tempC;
  }

  public void setTempC(String tempC) {
    this.tempC = tempC;
  }

  public String getFeelsLikeF() {
    return feelsLikeF;
  }

  public void setFeelsLikeF(String feelsLikeF) {
    this.feelsLikeF = feelsLikeF;
  }

  public String getFeelsLikeC() {
    return feelsLikeC;
  }

  public void setFeelsLikeC(String feelsLikeC) {
    this.feelsLikeC = feelsLikeC;
  }

  public String getHumidity() {
    return humidity;
  }

  public void setHumidity(String humidity) {
    this.humidity = humidity;
  }

  public String getCloud() {
    return cloud;
  }

  public void setCloud(String cloud) {
    this.cloud = cloud;
  }

  public String getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(String windSpeed) {
    this.windSpeed = windSpeed;
  }

  public String getVisibility() {
    return visibility;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }


}
