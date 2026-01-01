package com.devopspilot.devops_pilot.service;

import com.devopspilot.devops_pilot.dto.AiAnalysisResult;
import com.devopspilot.devops_pilot.enums.AnalysisStatus;
import com.devopspilot.devops_pilot.enums.ErrorCategory;
import com.devopspilot.devops_pilot.integration.OpenAiClient;
import com.devopspilot.devops_pilot.model.LogAnalysisRecord;
import com.devopspilot.devops_pilot.repository.LogAnalysisRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncAnalysisWorker {

    private final OpenAiClient openAiClient;
    private final LogAnalysisRepository repository;

    public AsyncAnalysisWorker(OpenAiClient openAiClient,
                               LogAnalysisRepository repository) {
        this.openAiClient = openAiClient;
        this.repository = repository;
    }

    @Async("analysisExecutor")
    public void processAnalysis(LogAnalysisRecord record) {

        try {
            record.setStatus(AnalysisStatus.PROCESSING);
            repository.save(record);

            AiAnalysisResult aiResult =
                    openAiClient.analyzeLog(record.getLogText());

            // Normalize
            ErrorCategory category =
                    aiResult.getErrorCategory() != null
                            ? aiResult.getErrorCategory()
                            : ErrorCategory.UNKNOWN;

            double confidence =
                    aiResult.getConfidence() != null
                            ? aiResult.getConfidence()
                            : 0.0;

            // Persist results
            record.setSummary(aiResult.getSummary());
            record.setRootCause(aiResult.getRootCause());
            record.setSuggestedFixes(aiResult.getSuggestedFixes());
            record.setErrorCategory(category);
            record.setConfidence(confidence);
            record.setStatus(AnalysisStatus.COMPLETED);

        } catch (Exception ex) {
            record.setStatus(AnalysisStatus.FAILED);
            record.setFailureReason(ex.getMessage());
        }

        repository.save(record);
    }
}

