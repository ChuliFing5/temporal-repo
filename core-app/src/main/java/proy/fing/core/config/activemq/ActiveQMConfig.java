package proy.fing.core.config.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@ComponentScan(basePackages = {"proy.fing.web"})
@Configuration
@EnableJms
public class ActiveQMConfig {
	
	@Value("${activemq.broken.url}")
	private String brokenUrl;
	
	@Value("${activemq.queueName}")
	private String queueName;

	@Bean
	public ActiveMQConnectionFactory getActiveMQConnectionFactory(){
		
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(this.brokenUrl);
		
		return factory;
	}
	
	@Bean(name="containerFactory")
    public DefaultJmsListenerContainerFactory myContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(getActiveMQConnectionFactory());
        factory.setConcurrency("3-10");
        return factory;
    }

}
