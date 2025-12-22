package com.devopspilot.devops_pilot.integration;

import com.devopspilot.devops_pilot.dto.AiAnalysisResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class OpenAiClient {
   private final WebClient webClient;
   private final String model;
   private final ObjectMapper objectMapper = new ObjectMapper();

   public OpenAiClient(WebClient openRouterWEbClient, @Value("${ai.openrouter.model}") String model){
       this.webClient=openRouterWEbClient;
       this.model=model;
   }
    public AiAnalysisResult analyzeLog(String logText) {

        /*
        // TEMPORARY STUB (next step will replace this)
        AiAnalysisResult result = new AiAnalysisResult();
        result.setSummary("AI analysis placeholder");
        result.setRootCause("OpenAI call not yet implemented");
        result.setSuggestedFixes(
                java.util.List.of("Implement OpenAI integration")
        );*/
        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of(
                                "role", "system",
                                "content",
                                """
                                        You are a senior DevOps engineer.
                                        Analyze the provided CI/CD log.
                                        
                                        Respond ONLY in valid JSON.
                                        Do NOT include explanations outside JSON.
                                        
                                        JSON schema:
                                        {
                                          "summary": string,
                                          "rootCause": string,
                                          "suggestedFixes": string[]
                                        }
                                        
                                        If unsure, use clear, conservative language.
                                        """
                        ),
                        Map.of(
                                "role", "user",
                                "content", logText
                        )
                )
        );

        try {
            Map response = webClient.post()
                    .uri("/chat/completions")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            String content =
                    (String) ((Map) ((Map) ((List) response.get("choices"))
                            .get(0)).get("message")).get("content");

            return parseSafely(content);

        } catch (Exception ex) {
            return fallback("AI service unavailable", ex.getMessage());
        }
    }
        private AiAnalysisResult parseSafely(String content) {
            try {
                return objectMapper.readValue(content, AiAnalysisResult.class);
            } catch (Exception e) {
                return fallback("Malformed AI response", content);
            }
        }

        private AiAnalysisResult fallback(String reason, String details) {
            AiAnalysisResult result = new AiAnalysisResult();
            result.setSummary(reason);
            result.setRootCause(details);
            result.setSuggestedFixes(
                    List.of("Review logs manually", "Retry analysis")
            );
            return result;

    }
}
