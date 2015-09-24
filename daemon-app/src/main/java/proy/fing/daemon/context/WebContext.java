package proy.fing.daemon.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@ComponentScan(basePackages = {"proy.fing.daemon"})
@Configuration
@EnableScheduling
public class WebContext extends WebMvcConfigurerAdapter{
	

	
}
