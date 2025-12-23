package com.devopspilot.devops_pilot.dto;

import com.devopspilot.devops_pilot.enums.ErrorCategory;

import java.util.List;

public class LogAnalysisResponse {

    private String summary;
    private String rootCause;
    private List<String> suggestedFixes;
    private ErrorCategory errorCategory;
    private double confidence;

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

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
