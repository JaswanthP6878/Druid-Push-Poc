package com.druidexample.producer;

public class DruidProducerMain {
	public static void main(String[] args) throws Exception {
        System.out.println("Running IOT devices Producer");
		DeviceRegistary dr = new DeviceRegistary(40, 50);
		ProducerService producer = new Producer(dr);
		
		// start running the producer
		new Thread(producer).start();
		
	
    }

}
