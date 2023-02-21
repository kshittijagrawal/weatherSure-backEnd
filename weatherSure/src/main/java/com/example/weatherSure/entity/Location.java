package com.example.weatherSure.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Document(collection = "location")
public class Location implements Serializable {

    @Id
    private String name;
    private String region;
    private String dateTimeUpdated;
    private CurrentData currentData;
    private List<DailyData> dailyData;


    public List<DailyData> getDailyData() {
        return dailyData;
    }

    public void setDailyData(List<DailyData> dailyData) {
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

    public String getDateTimeUpdated() {
        return dateTimeUpdated;
    }

    public void setDateTimeUpdated(String dateTimeUpdated) {
        this.dateTimeUpdated = dateTimeUpdated;
    }

    public CurrentData getCurrentData() {
        return currentData;
    }

    public void setCurrentData(CurrentData currentData) {
        this.currentData = currentData;
    }


//    private String localDay;
//    private String localDate;
//    private String localTime;
}
