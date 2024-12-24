package com.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
		"com.app.dao",
		"com.app.controller",
		"com.app.converter"
})

public class AppConfig {
}
