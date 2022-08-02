import org.apache.kafka.clients.producer.*;

import java.util.Properties;


public class Productor {

    public static void main(String[] args) {

        Properties props = new Properties();

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9093,localhost:9094");
        props.put("retries",  0);
        props.put("batch.size", 16384);
        props.put("buffer.memory", 33554432);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        String topic = "prueba";
        int partition = 0;
        String key = "testKey";
        String value = "testValue";
        producer.send(new ProducerRecord<>(topic, partition,key,value));
        /*try {
            producer.send(new ProducerRecord<>(topic, partition,key,value)).get();
        } catch (InterruptedException  | ExecutionException e) {
            e.printStackTrace();
        }*/

        /*final ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(new ProducerRecord<>(topic, partition,key,value));
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if (e != null) {
                    System.out.print("Send failed for record");
                }
            }
        });*/
        producer.close();
    }
}

