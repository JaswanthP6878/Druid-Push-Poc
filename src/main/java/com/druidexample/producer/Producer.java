package com.druidexample.producer;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    
    private int nrMessages;
    public Producer(int nrMessages) throws IOException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.21:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(props);
        this.nrMessages = nrMessages;
    }
    public void sendMessages() throws JsonProcessingException {
        Random random = new Random();
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < nrMessages; i++) {
            TelemetryDataPoint tdp = new TelemetryDataPoint(random.nextInt(100));
            tdp.setTime(new DateTime(DateTimeZone.UTC).toString());
            tdp.setPressureValue(random.nextDouble());
            tdp.setTempValue(random.nextDouble());
//            System.out.println(objectMapper.writeValueAsString(tdp));
            System.out.println("data sent");
            // send to Kafka Queue
            producer.send(new ProducerRecord("test1", objectMapper.writeValueAsString(tdp)));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        producer.close();
    }
}



