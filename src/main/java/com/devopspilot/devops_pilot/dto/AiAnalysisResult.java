package com.devopspilot.devops_pilot.dto;

import com.devopspilot.devops_pilot.enums.ErrorCategory;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;
@Data
@JsonPropertyOrder({ "summary", "rootCause", "suggestedFixes","errorCategory","confidence"})
public class AiAnalysisResult {

    private String summary;
    private String rootCause;
    private ErrorCategory errorCategory;
    private Double confidence;
    private List<String> suggestedFixes;
}

