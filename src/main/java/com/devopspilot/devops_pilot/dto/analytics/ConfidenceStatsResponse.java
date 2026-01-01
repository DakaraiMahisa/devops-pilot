package com.devopspilot.devops_pilot.dto.analytics;

import com.devopspilot.devops_pilot.enums.ErrorCategory;

public class ConfidenceStatsResponse {

    private ErrorCategory category;
    private double averageConfidence;

    public ErrorCategory getCategory() {
        return category;
    }

    public void setCategory(ErrorCategory category) {
        this.category = category;
    }

    public double getAverageConfidence() {
        return averageConfidence;
    }

    public void setAverageConfidence(double averageConfidence) {
        this.averageConfidence = averageConfidence;
    }
}
