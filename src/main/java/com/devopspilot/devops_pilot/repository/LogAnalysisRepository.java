package com.devopspilot.devops_pilot.repository;
import com.devopspilot.devops_pilot.enums.ErrorCategory;
import com.devopspilot.devops_pilot.model.LogAnalysisRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface LogAnalysisRepository extends MongoRepository<LogAnalysisRecord,String>{
    Page<LogAnalysisRecord> findByErrorCategory(ErrorCategory category, Pageable pageable);

   Page<LogAnalysisRecord> findByPipelineTypeIgnoreCase(
           String pipelineType,
           Pageable pageable);


    List<LogAnalysisRecord> findByCreatedAtBetween(
            Instant start,
            Instant end
    );

    Page<LogAnalysisRecord> findByErrorCategoryAndPipelineTypeIgnoreCase(
            ErrorCategory category,
            String pipelineType,
            Pageable pageable);
}
