package com.devopspilot.devops_pilot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LogAnalysisRequest {

    @NotBlank
    private String pipelineType;

    @NotBlank
    @Size(min=20,max=50000)
    private String logText;
}
