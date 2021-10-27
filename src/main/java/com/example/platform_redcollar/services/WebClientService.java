package com.example.platform_redcollar.services;

import common.lib.models.dto.response.PersonDtoResponse;
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

    private final WebClient webClient;

    @Autowired
    public WebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<PersonDtoResponse> getPersonGenres(String genres) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(requestGenres)
                        .queryParam("genres", genres)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PersonDtoResponse>>() {
                }).block();
    }
}
