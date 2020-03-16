package com.twl.pollservice.integration;

import com.twl.pollservice.integration.response.UserInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserInfoIntegration {

    @Value("${user-info.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public UserInfoIntegration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserInfoResponse checkCpfStatus(String cpf) {
        String url = baseUrl + cpf;
        return restTemplate.getForObject(url, UserInfoResponse.class);
    }
}
