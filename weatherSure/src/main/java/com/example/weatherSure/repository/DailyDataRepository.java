package com.example.weatherSure.repository;

import com.example.weatherSure.entity.DailyData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyDataRepository extends MongoRepository<DailyData, String> {
}
