package com.study.rabbitmq.main;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

	public static void main(String [] args) throws IOException, TimeoutException, InterruptedException {
		//建立客户端连接
		ConnectionFactory connectionFactory = new ConnectionFactory();
		//配置连接参数
		connectionFactory.setHost("192.168.70.130");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		//建立连接
		Connection connection = connectionFactory.newConnection();
		//建立通讯渠道
		Channel channel = connection.createChannel();
		//定义队列
		String queueName = "queue01";
		//为channel定义队列属性
		channel.queueDeclare(queueName, false, false, false, null);
		//发送消息
		for(int i = 0 ; i < 10 ; i++) {
			String msg = "Hello World RabbitMQ" + i;
			channel.basicPublish("", queueName, null, msg.getBytes());
			System.err.println("发送的数据：" + msg);
			TimeUnit.SECONDS.sleep(1);
		}
		channel.close();
		connection.close();
	}
}
