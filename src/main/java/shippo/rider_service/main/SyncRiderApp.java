package shippo.rider_service.main;

import akka.actor.ActorRef;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.ConfigLoader;
import shippo.rider_service.kafka.KafkaRef;
import shippo.global.kafka.SingleConsumer;
import shippo.rider_service.sync.actor.ActorManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
* Load static env parameter (database name, url endpoint, ...) from config file.
* */
public class SyncRiderApp{
    static Logger LOG = LoggerFactory.getLogger(SyncRiderApp.class);
    public SyncRiderApp() {
    }

    public static void main(String[] args) {
        Properties properties = ConfigLoader.getInstance().getProperties();
        String brokerList = properties.getProperty("kafka.brokerlist");
        String[] topics = properties.getProperty("kafka.rider.topic").split(",");


        List<String> listTopic = new ArrayList();
        for(String topic: topics){
            listTopic.add(topic.trim());
            LOG.info("Add topic {}",topic);
        }
        String kafkaGroup = properties.getProperty("kafka.rider.group");

        LOG.info("Loaded oauth configuration: brokerList - {},topics - {}, kafkaGroup - {}"
                ,brokerList, listTopic, kafkaGroup);

        new SingleConsumer(brokerList, kafkaGroup, listTopic) {
            @Override
            public void processMsg(ConsumerRecord<byte[], byte[]> record)
            {
                if(KafkaRef.topicMapper.containsKey(record.topic())) {
                    ActorRef actorRef = ActorManager.getActor(KafkaRef.topicMapper.get(record.topic()));
                    actorRef.tell(new String(record.value()), ActorRef.noSender());
                }
            }
        };
    }
}
