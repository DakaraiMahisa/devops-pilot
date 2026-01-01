package com.devopspilot.devops_pilot.dto.analytics;

public class CountByPipelineResponse {

    private String pipelineType;
    private long count;

    public String getPipelineType() {
        return pipelineType;
    }

    public void setPipelineType(String pipelineType) {
        this.pipelineType = pipelineType;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
