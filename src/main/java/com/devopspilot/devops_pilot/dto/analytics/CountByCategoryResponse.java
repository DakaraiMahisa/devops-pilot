package com.devopspilot.devops_pilot.dto.analytics;

import com.devopspilot.devops_pilot.enums.ErrorCategory;

public class CountByCategoryResponse {
    private ErrorCategory category;
    private long count;

    public ErrorCategory getCategory() {
        return category;
    }

    public void setCategory(ErrorCategory category) {
        this.category = category;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
