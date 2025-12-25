package com.devopspilot.devops_pilot.model;

import com.devopspilot.devops_pilot.enums.ErrorCategory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

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

    // ====== GETTERS & SETTERS ======

    public String getId() {
        return id;
    }

    public String getPipelineType() {
        return pipelineType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPipelineType(String pipelineType) {
        this.pipelineType = pipelineType;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public List<String> getSuggestedFixes() {
        return suggestedFixes;
    }

    public void setSuggestedFixes(List<String> suggestedFixes) {
        this.suggestedFixes = suggestedFixes;
    }

    public ErrorCategory getErrorCategory() {
        return errorCategory;
    }

    public void setErrorCategory(ErrorCategory errorCategory) {
        this.errorCategory = errorCategory;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Instant getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(Instant analyzedAt) {
        this.analyzedAt = analyzedAt;
    }
}
