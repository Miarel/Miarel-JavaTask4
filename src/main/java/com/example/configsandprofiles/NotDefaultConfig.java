package com.example.configsandprofiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnExpression("'${spring.application.configuration}'!='default'")
@ConfigurationProperties(prefix = "spring.application")
public class NotDefaultConfig {
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

    @Bean("notDefault")
    private void info() {
        System.out.println(String.format("Bean: %s\nProfile: %s\nList values: %s\nConfig: %s\nApp name: %s",
                NotDefaultConfig.class.getName(), Arrays.toString(environment.getActiveProfiles()), getValues(), config, name));
    }
}
