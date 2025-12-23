package com.devopspilot.devops_pilot.dto;

import com.devopspilot.devops_pilot.enums.ErrorCategory;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
@JsonPropertyOrder({ "summary", "rootCause", "suggestedFixes","errorCategory","confidence"})
public class AiAnalysisResult {

    private String summary;
    private String rootCause;
    private ErrorCategory errorCategory;
    private Double confidence;
    private List<String> suggestedFixes;

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
}

