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


### Druid Sercice SQL calls
Sql Calls can be made to localhost:8888,

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

