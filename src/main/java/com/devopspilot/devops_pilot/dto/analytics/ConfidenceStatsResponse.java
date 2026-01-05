package com.devopspilot.devops_pilot.dto.analytics;

import com.devopspilot.devops_pilot.enums.ErrorCategory;
import lombok.Data;

@Data
public class ConfidenceStatsResponse {

    private ErrorCategory category;
    private double averageConfidence;




}
