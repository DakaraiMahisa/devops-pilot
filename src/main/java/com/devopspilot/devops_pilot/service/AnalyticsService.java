package com.devopspilot.devops_pilot.service;

import com.devopspilot.devops_pilot.dto.analytics.ConfidenceStatsResponse;
import com.devopspilot.devops_pilot.dto.analytics.CountByCategoryResponse;
import com.devopspilot.devops_pilot.dto.analytics.CountByPipelineResponse;
import com.devopspilot.devops_pilot.dto.analytics.DailyTrendResponse;
import com.devopspilot.devops_pilot.repository.LogAnalysisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {
    private final LogAnalysisRepository repository;
    public AnalyticsService(LogAnalysisRepository repository){
        this.repository = repository;
    }
    public List<CountByCategoryResponse> countByCategory() {
        return repository.countByErrorCategory()
                .stream()
                .map(r -> {
                    CountByCategoryResponse dto = new CountByCategoryResponse();
                    dto.setCategory(r.getId());
                    dto.setCount(r.getCount());
                    return dto;
                })
                .toList();
    }

    public List<CountByPipelineResponse> countByPipeline() {
        return repository.countByPipelineType()
                .stream()
                .map(r -> {
                    CountByPipelineResponse dto = new CountByPipelineResponse();
                    dto.setPipelineType(r.getId());
                    dto.setCount(r.getCount());
                    return dto;
                })
                .toList();
    }
    public List<ConfidenceStatsResponse> confidenceByCategory() {
        return repository.averageConfidenceByCategory()
                .stream()
                .map(r -> {
                    ConfidenceStatsResponse dto = new ConfidenceStatsResponse();
                    dto.setCategory(r.getId());
                    Double avg = r.getAvgConfidence();
                    dto.setAverageConfidence(avg != null ? avg : 0.0);
                    return dto;
                })
                .toList();
    }

    public List<DailyTrendResponse> dailyTrend() {
        return repository.dailyTrend()
                .stream()
                .map(r -> {
                    DailyTrendResponse dto = new DailyTrendResponse();
                    dto.setDate(r.getId());
                    dto.setCount(r.getCount());
                    return dto;
                })
                .toList();
    }

}
