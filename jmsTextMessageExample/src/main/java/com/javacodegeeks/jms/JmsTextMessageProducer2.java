package com.javacodegeeks.jms;

import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsTextMessageProducer2 {
	public static void main(String[] args) throws URISyntaxException, Exception {
		Connection connection = null;
		try {
			// Producer
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("customerQueue");
			MessageProducer producer = session.createProducer(queue);
			String payload = "Hi, I am text message";
			TextMessage textMsg = session.createTextMessage();
			textMsg.setText(payload);
			System.out.println("Sending text '" + payload + "'");
			producer.send(textMsg);
			session.close();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
}
