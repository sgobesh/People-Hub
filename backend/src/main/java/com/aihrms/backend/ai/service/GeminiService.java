package com.aihrms.backend.ai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String askGemini(String prompt) {
String url =
"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
+ apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

       Map<String, Object> requestBody = Map.of(
        "contents",
        List.of(
                Map.of(
                        "parts",
                        List.of(
                                Map.of(
                                        "text",
                                        prompt
                                )
                        )
                )
        )
);
        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

       ResponseEntity<Map> response;

try {

    response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            Map.class
    );

} catch (org.springframework.web.client.HttpStatusCodeException e) {

    System.out.println("Google Error:");
    System.out.println("Status : " + e.getStatusCode());
System.out.println("Body   : " + e.getResponseBodyAsString());

    throw e;
}

        try {

            List candidates =
                    (List) response.getBody().get("candidates");

            Map candidate = (Map) candidates.get(0);

            Map content =
                    (Map) candidate.get("content");

            List parts =
                    (List) content.get("parts");

            Map firstPart =
                    (Map) parts.get(0);

            return firstPart.get("text").toString();

        } catch (Exception e) {

            return "No response from Gemini.";
        }
    }
}