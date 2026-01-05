package com.devopspilot.devops_pilot.model;

import com.devopspilot.devops_pilot.enums.ErrorCategory;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
@Data
@Document(collection = "log_analysis")
public class LogAnalysis {

    @Id
    private String id;

    private String pipelineType;
    private String logText;

    private String summary;
    private String rootCause;
    private List<String> suggestedFixes;

    private ErrorCategory errorCategory;
    private Double confidence;

    private Instant analyzedAt;


}
