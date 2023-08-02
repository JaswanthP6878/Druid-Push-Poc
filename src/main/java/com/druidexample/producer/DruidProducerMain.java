package com.druidexample.producer;

public class DruidProducerMain {
	public static void main(String[] args) throws Exception {
        System.out.println("Running IOT devices Producer");
//        ProducerService producer = new Producer();
//        ProducerService producer2 = new Producer();    
//        new Thread(producer).start();
//        new Thread(producer2).start();
        
		DeviceRegistary dr = new DeviceRegistary(20, 30);
//		DeviceRegistary dr1 = new DeviceRegistary(30, 40);
		
		ProducerService producer = new Producer(dr);
		
		new Thread(producer).start();
    }

}
