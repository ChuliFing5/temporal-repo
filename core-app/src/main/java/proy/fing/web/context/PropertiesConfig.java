package proy.fing.web.context;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertiesConfig {
	
	@Bean
	public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer(){
		
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		propertyPlaceholderConfigurer.setLocation(new ClassPathResource("/config/core-app.properties"));
		return propertyPlaceholderConfigurer;
	}

}
