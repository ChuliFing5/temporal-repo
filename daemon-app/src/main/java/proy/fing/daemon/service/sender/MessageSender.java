package proy.fing.daemon.service.sender;

import org.springframework.jms.core.JmsTemplate;

import com.google.gson.Gson;


public class MessageSender {
	
	private JmsTemplate jmsTemplate;

	public void send(Object message){
		
		MessageBuilder messageBuilder = new MessageBuilder();
		messageBuilder.setConverter(new Gson());
		messageBuilder.setObjectToSend(message);
		this.jmsTemplate.send(messageBuilder);
		
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate){
		this.jmsTemplate = jmsTemplate;
	}
}
