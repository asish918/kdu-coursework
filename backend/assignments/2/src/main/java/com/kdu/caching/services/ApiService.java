package com.kdu.caching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kdu.caching.dtos.ApiDTO;

@Service
public class ApiService {

        private final WebClient webClient;

        @Autowired
        private Environment env;

        @Autowired
        public ApiService(WebClient.Builder webClientBuilder) {
                this.webClient = webClientBuilder.baseUrl(
                                "http://api.positionstack.com/v1")
                                .build();
        }

        public ApiDTO forwardGeoCodeFromApi(String location) {
                return webClient.get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/forward")
                                                .queryParam("access_key", env.getProperty(
                                                                "api-key"))
                                                .queryParam("query", location)
                                                .build())
                                .retrieve()
                                .bodyToMono(ApiDTO.class)
                                .block();
        }

        public ApiDTO reverseGeoCodeFromApi(String latitude, String longitude) {
                return webClient.get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/reverse")
                                                .queryParam("access_key", env.getProperty(
                                                                "api-key"))
                                                .queryParam("query", latitude + "," + longitude)
                                                .build())
                                .retrieve()
                                .bodyToMono(ApiDTO.class)
                                .block();
        }
}
