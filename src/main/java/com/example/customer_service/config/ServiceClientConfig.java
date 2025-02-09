package com.example.customer_service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@Slf4j
public class ServiceClientConfig {

    @Bean
    public RestClient restClient(@Value("${movie.service.url}") String baseUrl){
        log.info("Base URL {}",baseUrl);
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
