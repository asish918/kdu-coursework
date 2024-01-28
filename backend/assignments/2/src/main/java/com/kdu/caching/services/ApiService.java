package com.kdu.caching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kdu.caching.dtos.ApiDTO;

/**
 * Service Bean for calling the PositionStack API
 */
@Service
public class ApiService {
        private final WebClient webClient;
        private Environment env;

        /**
         * WebClient and Environment are injected to access the
         * application.properties file and WebClient Builder Beans
         * @param webClientBuilder {@link com.kdu.caching.configs.WebClientConfig  WebClient Bean}
         */
        @Autowired
        public ApiService(WebClient.Builder webClientBuilder, Environment env) {
                this.env = env;
                this.webClient = webClientBuilder.baseUrl(
                                "http://api.positionstack.com/v1")
                                .build();
        }

        /**
         * Takes a Location string as params and forms an
         * API URL to call and get the Latitude and Longitudes
         * @param location Location to get the co-ordinates of
         * @return {@link com.kdu.caching.dtos.ApiDTO Response} of the API call
         */
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

        /**
         * Takes latitude and longitude as params and forms an
         * API URL to call and get the address
         * @param latitude Latitude string
         * @param longitude Longitude string
         * @return {@link com.kdu.caching.dtos.ApiDTO Response} of the API call
         */
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
