package com.devopspilot.devops_pilot.controller;
import com.devopspilot.devops_pilot.dto.LogAnalysisRequest;
import com.devopspilot.devops_pilot.dto.LogAnalysisResponse;
import com.devopspilot.devops_pilot.service.LogAnalysisService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/logs")
public class LogAnalysisController {


    private final LogAnalysisService service;

    public LogAnalysisController(LogAnalysisService service){
        this.service = service;
    }
    @PostMapping("/analyze")
    public LogAnalysisResponse analyze(@Valid @RequestBody LogAnalysisRequest request) {
        return service.analyze(request);
    }
}

