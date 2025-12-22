package com.devopspilot.devops_pilot.repository;

import com.devopspilot.devops_pilot.model.LogAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogAnalysisRepository extends MongoRepository<LogAnalysis,String>{
}
