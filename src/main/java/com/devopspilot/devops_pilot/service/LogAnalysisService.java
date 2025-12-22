package com.devopspilot.devops_pilot.service;


import com.devopspilot.devops_pilot.dto.AiAnalysisResult;
import com.devopspilot.devops_pilot.dto.LogAnalysisRequest;
import com.devopspilot.devops_pilot.dto.LogAnalysisResponse;

import com.devopspilot.devops_pilot.integration.OpenAiClient;
import org.springframework.stereotype.Service;

@Service
public class LogAnalysisService {

    private final OpenAiClient openAiClient;

    public LogAnalysisService(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    public LogAnalysisResponse analyze(LogAnalysisRequest request){
        AiAnalysisResult aiResult =
                openAiClient.analyzeLog(request.getLogText());

        LogAnalysisResponse response = new LogAnalysisResponse();
        response.setSummary(aiResult.getSummary());
        response.setRootCause(aiResult.getRootCause());
        response.setSuggestedFixes(aiResult.getSuggestedFixes());

        return response;
    }
}
