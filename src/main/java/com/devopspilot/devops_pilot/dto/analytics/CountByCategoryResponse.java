package com.devopspilot.devops_pilot.dto.analytics;

import com.devopspilot.devops_pilot.enums.ErrorCategory;
import lombok.Data;

@Data
public class CountByCategoryResponse {
    private ErrorCategory category;
    private long count;

}
