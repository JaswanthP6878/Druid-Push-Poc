package com.druidexample.producer;

public class DruidProducerMain {
	public static void main(String[] args) throws Exception {
        System.out.println("Running IOT devices Producer");
        ProducerService producer = new Producer();
        ProducerService producer2 = new Producer();    
        
        new Thread(producer).start();
        new Thread(producer2).start();

    }

}
