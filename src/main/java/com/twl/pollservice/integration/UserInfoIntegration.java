package com.twl.pollservice.integration;

import com.twl.pollservice.integration.response.UserInfoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserInfoIntegration {

    private final RestTemplate restTemplate;

    public UserInfoIntegration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserInfoResponse checkCpfStatus(String cpf) {
        String url = "https://user-info.herokuapp.com/users/" + cpf;
        return restTemplate.getForObject(url, UserInfoResponse.class);
    }
}
