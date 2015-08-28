package proy.fing.daemon.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import proy.fing.daemon.service.sender.MessageSender;

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
	
	@Bean(name="jmsQueue")
	public ActiveMQQueue getQueue(){
		return new ActiveMQQueue(this.queueName);
	}
	
	@Bean
    public JmsTemplate getJmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDefaultDestination(getQueue());
        jmsTemplate.setConnectionFactory(getActiveMQConnectionFactory());
        return jmsTemplate;
    }
	
	@Bean
	public MessageSender getMessageSender(){
		MessageSender messageSender = new MessageSender();
		messageSender.setJmsTemplate(getJmsTemplate());
		return messageSender;
	}

}
