package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
public class TemplateConfig {

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }


}