package com.study.rabbitmq.main;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.Basic.Deliver;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Receiver {

	
	public static void main(String [] args) throws Exception {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("192.168.70.130");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		
		Connection connection = connectionFactory.newConnection();
		
		Channel channel = connection.createChannel();
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		String queueName = "queue01";
		
		channel.basicConsume(queueName,true,consumer);
		
		while(true) {
			
			//获取消息，如果没有消息，这一步将会阻塞
			Delivery  delivery = consumer.nextDelivery();
			String msg = new String(delivery.getBody());
			System.out.println("收到消息"+msg);
			
		}
		
	}
}
