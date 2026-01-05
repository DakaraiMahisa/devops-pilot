package com.devopspilot.devops_pilot.dto.analytics;

import lombok.Data;

@Data
public class CountByPipelineResponse {

    private String pipelineType;
    private long count;


}
