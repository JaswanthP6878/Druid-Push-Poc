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



### Setting Up Hadoop as the deep storage database;

we first download and tar the hadoop version 2.10.2
we change, etc/hadoop/core-site.xml to,

```xml
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>
```
and, etc/hadoop/hdfs-site.xml

```xml
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
</configuration>
```

after wards we need to format the filesystem for the namenode;

```bash
 bin/hdfs namenode -format
```

to start and stop the dfs deamon

```bash
## to start
sbin/start-dfs.sh
## to stop
sbin/stop-dfs.sh
```
We can make directories in the hadoop distributed Files sytem using

```bash
bin/hdfs dfs -mkdir input
// we can make a root path like
bin/hdfs dfs -mkdir -p /user/root
```
we create two paths for the druid project
- druid/segments, to store data segments
- druid/index-logs, to store the indexing logs that are used for querying.

next we copy all the .xml files from
etc/hadoop/*.xml to conf/druid/single-server/nano-quickstart/_common, in the druid system

and we change the druid.storage commands in conf/druid/single-server/nano-quickstart/_common/common.runtime.properties

```bash
druid.storage.type = hdfs
druid.storage.storageDirectory = hdfs://localhost:9000/user/root/druid/segments

// and for indexs

druid.indexer.logs.type = hdfs
druid.indexer.logs.directory= hdfs://localhost:9000/user/root/druid/index-logs
```
### Running the entire application

First, run the hdfs file system, then the druid system and then the kafka queue

```bash
// 1 inside the hadoop2.10.2 folder
sbin/start-dfs.sh

//2  inside the druid folder
/bin/start-nano-quickstart

//3  inside the kafka folder.
./bin/kafka-server-start.sh config/server.properties
```

> application in the current state is working and is able to use the hadoop DFS as its deep storage.
 


### Appendix- useful commands

to learn where the java sdk is 

```bash
whereis javac
```

to get the complete directory

```bash
readlink -f /usr/bin/javac
```
the "/usr/bin/javac" is the output from above command (whereis javac)

to get the list of all the places where java sdks are 

```bash
update-alternatives --list java
```





