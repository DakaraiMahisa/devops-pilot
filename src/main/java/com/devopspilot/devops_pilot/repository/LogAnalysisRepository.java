package com.devopspilot.devops_pilot.repository;
import com.devopspilot.devops_pilot.enums.ErrorCategory;
import com.devopspilot.devops_pilot.model.LogAnalysisRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
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

    @Aggregation(pipeline = {
            "{ $group: { _id: '$errorCategory', count: { $sum: 1 } } }"
    })
    List<CategoryCount> countByErrorCategory();
    public interface CategoryCount {
        ErrorCategory getId();
        long getCount();
    }
    @Aggregation(pipeline = {
            "{ $group: { _id: '$pipelineType', count: { $sum: 1 } } }"
    })
    List<PipelineCount> countByPipelineType();
    public interface PipelineCount {
        String getId();
        long getCount();
    }
    @Aggregation(pipeline = {
            "{ $match: { confidence: { $ne: null } } }",
            "{ $group: { _id: '$errorCategory', avgConfidence: { $avg: '$confidence' } } }"
    })
    List<ConfidenceStat> averageConfidenceByCategory();
    public interface ConfidenceStat {
        ErrorCategory getId();
        Double getAvgConfidence();
    }

    @Aggregation(pipeline = {
            "{ $group: { _id: { $dateToString: { format: '%Y-%m-%d', date: '$createdAt' } }, count: { $sum: 1 } } }",
            "{ $sort: { '_id': 1 } }"
    })
    List<DailyTrend> dailyTrend();
    public interface DailyTrend {
        String getId();
        long getCount();
    }

}
