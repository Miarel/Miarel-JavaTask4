package com.example.configsandprofiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnBean(TestProfile.class)
@ConfigurationProperties(prefix = "spring.application")
public class TestProfileExists {
    private List<String> values;
    @Value("${spring.application.name}")
    private String name;
    @Value("${spring.application.configuration}")
    private String config;
    @Autowired
    private Environment environment;

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Bean("testExists")
    private void info() {
        System.out.println(String.format("Bean: %s\nProfile: %s\nList values: %s\nConfig: %s\nApp name: %s\n",
                TestProfileExists.class.getName(), Arrays.toString(environment.getActiveProfiles()), getValues(), config, name));
    }
}
