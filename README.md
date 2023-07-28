## Setup Guide
First Start The Druid, from apache-druid-26.0.0 folder

```bash
bin/start-nano-quickstart
```
Then Start Kafka server (version/folder kafka_2.13-2.7.0)

```bash
./bin/kafka-server-start.sh config/server.properties
```
> Both are running on the same zookeper instance so the order matters

Then run the DruidProducerMain.java file to populate the queue on the topic mentioned in the code. 

> **During Closing first close the Kafka Server, then the druid server**.

