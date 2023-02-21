package com.example.weatherSure.repository;

import com.example.weatherSure.entity.CurrentData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDataRepository extends MongoRepository<CurrentData, String> {
}
