package com.example.platform_redcollar.services;

import common.lib.models.dto.response.PersonDtoResponse;
import common.lib.services.KeycloakApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class WebClientService {
    @Value("${microservices.url.get.genres}")
    private String requestGenres;
    @Value("${profiles.keycloak.service.username}")
    private String serviceLogin;
    @Value("${profiles.keycloak.service.password}")
    private String servicePassword;

    private final WebClient webClient;
    private final KeycloakApiService keycloakApiService;

    @Autowired
    public WebClientService(WebClient webClient, KeycloakApiService keycloakApiService) {
        this.webClient = webClient;
        this.keycloakApiService = keycloakApiService;
    }

    public List<PersonDtoResponse> getPersonGenres(String genres) {

        String token = keycloakApiService.getAccessToken(serviceLogin, servicePassword);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(requestGenres)
                        .queryParam("genres", genres)
                        .build())
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PersonDtoResponse>>() {
                }).block();
    }
}
