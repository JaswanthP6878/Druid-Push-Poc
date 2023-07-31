package com.druidexample.producer;

public class DruidProducerMain {
	public static void main(String[] args) throws Exception {
        System.out.println("Run IOT devices Producer");
        ProducerService producer = new Producer(Integer.parseInt("10"));
        producer.sendMessages();

    }

}
