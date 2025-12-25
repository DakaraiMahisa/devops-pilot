package com.devopspilot.devops_pilot.service;


import com.devopspilot.devops_pilot.dto.AiAnalysisResult;
import com.devopspilot.devops_pilot.dto.LogAnalysisRequest;
import com.devopspilot.devops_pilot.dto.LogAnalysisResponse;

import com.devopspilot.devops_pilot.enums.ErrorCategory;
import com.devopspilot.devops_pilot.integration.OpenAiClient;

import com.devopspilot.devops_pilot.model.LogAnalysis;
import com.devopspilot.devops_pilot.model.LogAnalysisRecord;
import com.devopspilot.devops_pilot.repository.LogAnalysisRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LogAnalysisService {

    private final OpenAiClient openAiClient;
    private final LogAnalysisRepository repository;
    public LogAnalysisService(OpenAiClient openAiClient,LogAnalysisRepository repository) {
        this.openAiClient = openAiClient;
        this.repository=repository;
    }
    public LogAnalysisResponse analyze(LogAnalysisRequest request){
// 1. Call AI
        AiAnalysisResult aiResult =
                openAiClient.analyzeLog(request.getLogText());

        // 2. Normalize AI output
        if (aiResult.getErrorCategory() == null) {
            aiResult.setErrorCategory(ErrorCategory.UNKNOWN);
        }

        if (aiResult.getConfidence() == null ||
                aiResult.getConfidence() <= 0) {
            aiResult.setConfidence(0.0);
        }

        // 3. Build DOMAIN object (business meaning)
        LogAnalysis domain = new LogAnalysis();
        domain.setSummary(aiResult.getSummary());
        domain.setRootCause(aiResult.getRootCause());
        domain.setSuggestedFixes(aiResult.getSuggestedFixes());
        domain.setErrorCategory(aiResult.getErrorCategory());
        domain.setConfidence(aiResult.getConfidence());

        // 4. Build PERSISTENCE object (traceability)
        LogAnalysisRecord record = new LogAnalysisRecord();
        record.setPipelineType(request.getPipelineType());
        record.setLogText(request.getLogText());

        record.setSummary(domain.getSummary());
        record.setRootCause(domain.getRootCause());
        record.setSuggestedFixes(domain.getSuggestedFixes());
        record.setErrorCategory(domain.getErrorCategory());
        record.setConfidence(domain.getConfidence());

        record.setCreatedAt(Instant.now());

        // 5. Save to MongoDB
        repository.save(record);

        // 6. Build API response
        LogAnalysisResponse response = new LogAnalysisResponse();
        response.setSummary(domain.getSummary());
        response.setRootCause(domain.getRootCause());
        response.setSuggestedFixes(domain.getSuggestedFixes());
        response.setErrorCategory(domain.getErrorCategory());
        response.setConfidence(domain.getConfidence());

        return response;
    }
    public Page<LogAnalysisRecord> getByErrorCategory(
            ErrorCategory category,
            Pageable pageable) {
        return repository.findByErrorCategory(category, pageable);
    }

    public Page<LogAnalysisRecord> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


}
