package com.devopspilot.devops_pilot.controller;
import com.devopspilot.devops_pilot.dto.LogAnalysisRecordResponse;
import com.devopspilot.devops_pilot.enums.ErrorCategory;
import com.devopspilot.devops_pilot.repository.LogAnalysisRepository;
import com.devopspilot.devops_pilot.service.LogAnalysisService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/analyses")
public class AnalysisHistoryController {
    private final LogAnalysisService service;
    private final LogAnalysisRepository repository;
    public AnalysisHistoryController(LogAnalysisRepository repository,LogAnalysisService service){
        this.repository=repository;
        this.service=service;
    }
    @GetMapping
    public Page<LogAnalysisRecordResponse> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public LogAnalysisRecordResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/errors/category/{category}")
    public Page<LogAnalysisRecordResponse> byCategory(
            @PathVariable ErrorCategory category,
            Pageable pageable) {
        return service.getByErrorCategory(category,pageable);
    }

    @GetMapping("/pipeline/{pipelineType}")
    public Page<LogAnalysisRecordResponse> byPipeline(
            @PathVariable String pipelineType,
            Pageable pageable
    ) {
        return service.getByPipelineType(pipelineType, pageable);
    }

}
