package com.devopspilot.devops_pilot.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpenRouterConfig {

    @Bean
    public WebClient openRouterWebClient(
            @Value("${ai.openrouter.base-url}") String baseUrl,
            @Value("${ai.openrouter.api-key}") String apiKey) {

        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}