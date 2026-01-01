package com.devopspilot.devops_pilot.controller;
import com.devopspilot.devops_pilot.dto.LogAnalysisRequest;
import com.devopspilot.devops_pilot.service.LogAnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/logs")
public class LogAnalysisController {


    private final LogAnalysisService service;

    public LogAnalysisController(LogAnalysisService service){
        this.service = service;
    }
   /* @PostMapping("/analyze")
    public LogAnalysisResponse analyze(@Valid @RequestBody LogAnalysisRequest request) {
        return service.analyze(request);
    }*/

    @PostMapping("/analyze")
    public ResponseEntity<Map<String, String>> analyzeAsync(
            @RequestBody LogAnalysisRequest request) {

        String id = service.submitAsync(request);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(Map.of(
                        "analysisId", id,
                        "status", "PENDING"
                ));
    }

}

