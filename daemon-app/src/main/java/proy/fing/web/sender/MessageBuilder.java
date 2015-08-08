package proy.fing.web.sender;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.MessageCreator;

import com.google.gson.Gson;

public class MessageBuilder implements MessageCreator{
	
	private String message;
	private Gson converter;

	@Override
	public Message createMessage(Session session) throws JMSException {
		
		TextMessage textMessage = session.createTextMessage();
		textMessage.setText(message);
		return textMessage;
		
	}
	
	public void setObjectToSend(Object object){
		this.message = this.converter.toJson(object);
	}
	
	public void setConverter(Gson converter){
		this.converter = converter;
	}

}
