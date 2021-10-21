package com.example.platform_redcollar.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class ApplicationConfig {

    @Value("${microservices.url.test}")
    private String baseUrlTest;

    @Bean
    public WebClient webClientBean(){
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrlTest);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
        return WebClient.builder().uriBuilderFactory(factory).baseUrl(baseUrlTest).build();
    }
}
