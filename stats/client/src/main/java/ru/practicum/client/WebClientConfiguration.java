package ru.practicum.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ComponentScan
public class WebClientConfiguration {
    @Bean
        public WebClient webClient(@Value(value = "http://stats-server:9090") String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
