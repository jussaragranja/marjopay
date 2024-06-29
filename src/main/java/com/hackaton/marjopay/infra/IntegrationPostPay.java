package com.hackaton.marjopay.infra;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class IntegrationPostPay {

    private static final String EXTERNAL_API_URL = "https://hackathon.marjosports.com.br/hackathon";
    private static final String API_KEY = "HACKATON_UNIESP_MARJO_2024";

    public ResponseEntity<String> createExternalPayment(String cpf, String valor, String appName) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", API_KEY);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("cpf", cpf);
        requestBody.put("valor", valor);
        requestBody.put("app_name", appName);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(EXTERNAL_API_URL, requestEntity, String.class);

        return response;
    }

}
