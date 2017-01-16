package com.ecej.nove.sms.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import com.ecej.nove.utils.common.PropertyBootConfig;

@Configuration
@PropertySource(value = { "classpath:remote-db.properties", "classpath:remote-dubbo.properties" })
@ImportResource({ "classpath:dubbo/*.xml" })
public class PropertiesConfig {

	@PostConstruct
	public PropertyBootConfig propertyBootConfig() {
		return new PropertyBootConfig();
	}
}
