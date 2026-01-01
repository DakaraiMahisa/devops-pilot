package com.devopspilot.devops_pilot.controller;

import com.devopspilot.devops_pilot.dto.analytics.ConfidenceStatsResponse;
import com.devopspilot.devops_pilot.dto.analytics.CountByCategoryResponse;
import com.devopspilot.devops_pilot.dto.analytics.CountByPipelineResponse;
import com.devopspilot.devops_pilot.dto.analytics.DailyTrendResponse;
import com.devopspilot.devops_pilot.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/errors/category")
    public List<CountByCategoryResponse> byCategory() {
        return analyticsService.countByCategory();
    }

    @GetMapping("/pipelines")
    public List<CountByPipelineResponse> byPipeline() {
        return analyticsService.countByPipeline();
    }

    @GetMapping("/confidence/category")
    public List<ConfidenceStatsResponse> confidenceByCategory() {
        return analyticsService.confidenceByCategory();
    }

    @GetMapping("/trend/daily")
    public List<DailyTrendResponse> dailyTrend() {
        return analyticsService.dailyTrend();
    }

}
