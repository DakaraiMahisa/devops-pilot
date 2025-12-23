package com.devopspilot.devops_pilot.integration;

import com.devopspilot.devops_pilot.dto.AiAnalysisResult;
import com.devopspilot.devops_pilot.enums.ErrorCategory;
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
                                          "suggestedFixes": string[],
                                          "rootCause": string,
                                          "errorCategory": one of [
                                            "BUILD_CONFIGURATION",
                                            "DEPENDENCY_RESOLUTION",
                                            "ENVIRONMENT_MISMATCH",
                                            "TEST_FAILURE",
                                            "INFRASTRUCTURE",
                                            "PERMISSION_AUTH",
                                            "TIMEOUT_RESOURCE",
                                            "UNKNOWN"
                                          ],
                                          "confidence": number between 0.0 and 1.0
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
                String cleaned = cleanJson(content);
                return objectMapper.readValue(cleaned,AiAnalysisResult.class);
            } catch (Exception e) {
                return fallback("Malformed AI response", content);
            }
        }
    private String cleanJson(String content) {
        if (content == null) {
            return "";
        }

        content = content.trim();

        // Remove Markdown JSON fences if present
        if (content.startsWith("```")) {
            content = content
                    .replaceFirst("```json", "")
                    .replaceFirst("```", "")
                    .trim();
        }

        return content;
    }


    private AiAnalysisResult fallback(String reason, String details) {
            AiAnalysisResult result = new AiAnalysisResult();
            result.setSummary(reason);
            result.setRootCause(details);
            result.setErrorCategory(ErrorCategory.UNKNOWN);
            result.setConfidence(0.0);
            result.setSuggestedFixes(
                    List.of("Review logs manually", "Retry analysis")
            );
            return result;

    }
}
