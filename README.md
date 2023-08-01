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


### Druid Service SQL calls
SQL Calls can be made to localhost:8888,

```json
{
  "query" : "SELECT TIME_FLOOR(\"__time\", 'P1D') AS \"__time_by_day\" , AVG(\"tempValue\") AS \"avg_tempValue\" FROM \"test1\" GROUP BY 1 ORDER BY 2 DESC"
}
```
This Query gets average temperature on a hourly basis

to send the request we write use the following command (if we add the query into the query.json file)

```bash
curl -X POST -H'Content-Type: application/json' http://localhost:8888/druid/v2/sql/ -d @query.json
```

### Running Kafka and Druid in a linux machine.
for druid to get working we have to make sure that the port 8080 on the linux machine is not being utilized. we can verify this by

```bash
lsof -i :8080
```
and if some process is running we can kill it (if possible) using 

```bash
pkill -9 <proces id from lsof command>
```

For Kafka to work, we need to change server.properties so that it accepts listeners other than the ones from localhost, so we need to change the advertised.listners

```bash
advertised.listeners = PLAINTEXT://192.168.1.21.9092
```
> Here the IP address provided is of the linux machine running the kafka and druid instance.

