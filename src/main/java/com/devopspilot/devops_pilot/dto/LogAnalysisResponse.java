package com.devopspilot.devops_pilot.dto;

import com.devopspilot.devops_pilot.enums.ErrorCategory;
import lombok.Data;

import java.util.List;

@Data
public class LogAnalysisResponse {

    private String summary;
    private String rootCause;
    private List<String> suggestedFixes;
    private ErrorCategory errorCategory;
    private double confidence;


}
