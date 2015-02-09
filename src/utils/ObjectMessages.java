package utils;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ObjectMessages {
	
	/**
	 * This implementation converts a TextMessage back to a String, a
	 * ByteMessage back to a byte array, a MapMessage back to a Map,
	 * and an ObjectMessage back to a Serializable object. Returns
	 * the plain Message object in case of an unknown message type.
	 * @see #extractStringFromMessage
	 * @see #extractSerializableFromMessage
	 */
	public Object fromMessage(Message message) throws JMSException {
		if (message instanceof TextMessage) {
			return extractStringFromMessage((TextMessage) message);
		}
		else if (message instanceof ObjectMessage) {
			return extractSerializableFromMessage((ObjectMessage) message);
		}
		else {
			return message;
		}
	}

	/**
	 * Create a JMS ObjectMessage for the given Serializable object.
	 * @param object the Serializable object to convert
	 * @param session current JMS session
	 * @return the resulting message
	 * @throws JMSException if thrown by JMS methods
	 * @see javax.jms.Session#createObjectMessage
	 */
	protected ObjectMessage createMessageForSerializable(Serializable object, Session session) throws JMSException {
		return session.createObjectMessage(object);
	}
	
	/**
	 * Extract a Serializable object from the given {@link ObjectMessage}.
	 * @param message the message to convert
	 * @return the resulting Serializable object
	 * @throws JMSException if thrown by JMS methods
	 */
	protected Serializable extractSerializableFromMessage(ObjectMessage message) throws JMSException {
		return message.getObject();
	}
	

	/**
	 * Extract a String from the given TextMessage.
	 * @param message the message to convert
	 * @return the resulting String
	 * @throws JMSException if thrown by JMS methods
	 */
	protected String extractStringFromMessage(TextMessage message) throws JMSException {
		return message.getText();
	}

}
