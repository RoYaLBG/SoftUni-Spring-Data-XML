package com.example.objectmapping.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapperBasicEmployee() {
        ModelMapper mapper = new ModelMapper();

        return mapper;
    }

    @Bean
    public Date nowDate() {
        return new Date();
    }


}
