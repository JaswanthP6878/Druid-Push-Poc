package com.druidexample.producer;

import java.io.IOException;

public class DruidProducerMain {
	public static void main(String[] args) throws IOException {
        System.out.println("Run IOT devices Producer");
        Producer producer = new Producer(Integer.parseInt("1400"));
        producer.sendMessages();

    }

}
