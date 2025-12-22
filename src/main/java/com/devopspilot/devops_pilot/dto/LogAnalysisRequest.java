package com.devopspilot.devops_pilot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LogAnalysisRequest {

    @NotBlank
    private String pipelineType;

    @NotBlank
    @Size(min=20,max=50000)
    private String logText;

    public String getPipelineType() {
        return pipelineType;
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
}
