package com.example.objectmapping.configs;

import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {

    @Bean
    public GsonBuilder gsonBuilder() {
        GsonBuilder result = new GsonBuilder();

        return result;
    }

}
