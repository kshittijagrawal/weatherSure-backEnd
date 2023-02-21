package com.example.weatherSure.repository;

import com.example.weatherSure.entity.HourlyData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourlyDataRepository extends MongoRepository<HourlyData, String> {
}
