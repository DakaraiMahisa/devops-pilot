package com.devopspilot.devops_pilot.model;

import com.devopspilot.devops_pilot.enums.AnalysisStatus;
import com.devopspilot.devops_pilot.enums.ErrorCategory;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Document(collection="log_analyses")
public class LogAnalysisRecord {
    @Id
    private String id;

    @Indexed
    private String pipelineType;
    private String logText;

    private String summary;
    private String rootCause;

    @Indexed
    private ErrorCategory errorCategory;
    private Double confidence;

    private AnalysisStatus status;
    private String failureReason;
    private List<String> suggestedFixes;

    @Indexed
    private Instant createdAt;




}
