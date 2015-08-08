package proy.fing.web.sender;

import org.springframework.jms.core.JmsTemplate;


public class MessageSender {
	
	private JmsTemplate jmsTemplate;

	public void send(Object message){
		
		MessageBuilder messageBuilder = new MessageBuilder();
		messageBuilder.setObjectToSend(message);
		
		this.jmsTemplate.send(messageBuilder);
		
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate){
		this.jmsTemplate = jmsTemplate;
	}
}
