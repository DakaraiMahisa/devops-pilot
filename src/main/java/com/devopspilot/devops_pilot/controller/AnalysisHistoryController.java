package com.devopspilot.devops_pilot.controller;

import com.devopspilot.devops_pilot.enums.ErrorCategory;
import com.devopspilot.devops_pilot.model.LogAnalysisRecord;
import com.devopspilot.devops_pilot.repository.LogAnalysisRepository;
import com.devopspilot.devops_pilot.service.LogAnalysisService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public Page<LogAnalysisRecord> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public LogAnalysisRecord getById(@PathVariable String id){
        return repository.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Analysis not found"));
    }

    @GetMapping("/errors/category/{category}")
    public Page<LogAnalysisRecord> byCategory(
            @PathVariable ErrorCategory category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getByErrorCategory(category, pageable);
    }

}
