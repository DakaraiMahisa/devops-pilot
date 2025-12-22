package com.devopspilot.devops_pilot.integration;

import com.devopspilot.devops_pilot.dto.AiAnalysisResult;
import org.springframework.stereotype.Component;

@Component
public class OpenAiClient {


    public AiAnalysisResult analyzeLog(String logText) {

        // TEMPORARY STUB (next step will replace this)
        AiAnalysisResult result = new AiAnalysisResult();
        result.setSummary("AI analysis placeholder");
        result.setRootCause("OpenAI call not yet implemented");
        result.setSuggestedFixes(
                java.util.List.of("Implement OpenAI integration")
        );

        return result;
    }
}
