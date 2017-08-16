package com.ecej.nove.sms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.ecej.nove.base.config.AbstractPropertiesConfig;

@Configuration
@PropertySource(value = { "classpath:remote-db.properties" })
public class PropertiesConfig extends AbstractPropertiesConfig {
}
