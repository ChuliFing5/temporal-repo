package proy.fing.web.sender;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

public class MessageBuilder implements MessageCreator{
	
	private Object object;

	@Override
	public Message createMessage(Session session) throws JMSException {
		
		ObjectMessage objectMessage = session.createObjectMessage();
		objectMessage.setObject((Serializable) object);
		return objectMessage;
		
	}
	
	public void setObjectToSend(Object object){
		this.object = object;
	}

}
