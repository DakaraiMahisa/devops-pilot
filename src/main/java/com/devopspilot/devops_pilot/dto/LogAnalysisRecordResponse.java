package com.devopspilot.devops_pilot.dto;

import com.devopspilot.devops_pilot.enums.AnalysisStatus;
import com.devopspilot.devops_pilot.enums.ErrorCategory;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class LogAnalysisRecordResponse {

    private String id;
    private String pipelineType;
    private String summary;
    private String rootCause;
    private List<String> suggestedFixes;
    private ErrorCategory errorCategory;
    private Double confidence;
    private Instant createdAt;
    private AnalysisStatus status;

}
