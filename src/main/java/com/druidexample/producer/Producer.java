package com.druidexample.producer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
public class Producer implements ProducerService {
    private KafkaProducer<String, String> producer;
    
    private DeviceRegistary dr;
    
//    private int nrMessages;
    public Producer(DeviceRegistary dr) throws IOException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.21:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(props);
        this.dr = dr;
    }
    public void run() {
    	try {
        Random random = new Random();
        ObjectMapper objectMapper = new ObjectMapper();
        int len = dr.getDeviceIds().size(); int min = dr.getDeviceIds().get(0); int max = dr.getDeviceIds().get(len - 1);
        while (true) {
            TelemetryDataPoint tdp = new TelemetryDataPoint(random.nextInt(max-min) + min);
            tdp.setTime(new DateTime(DateTimeZone.UTC).toString());
            tdp.setPressureValue(random.nextDouble()*100);
            tdp.setTempValue(random.nextDouble()*100);
//            System.out.println(objectMapper.writeValueAsString(tdp));
            System.out.println("data sent");
            // send to Kafka Queue
            producer.send(new ProducerRecord<String, String>("test_hdfs", objectMapper.writeValueAsString(tdp)));
//            Thread.sleep(100);
        }
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		producer.close();
    	}
 
        
    }
		
	}



