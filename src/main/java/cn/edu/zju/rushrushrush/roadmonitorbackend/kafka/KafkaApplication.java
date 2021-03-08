package cn.edu.zju.rushrushrush.roadmonitorbackend.kafka;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.test.test.KafkaCommonConfiguration;
import com.test.test.KafkaProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@Import({ KafkaCommonConfiguration.class, KafkaProperties.class })
@EnableKafka
//$KAFKA_HOME/bin/kafka-topics.sh --zookeeper docker-compose_zookeeper_1:2181 --describe --topic Topic2
//$KAFKA_HOME/bin/kafka-console-producer.sh --topic=Topic1 --broker-list docker-compose_kafka_1:9092
//$KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server docker-compose_kafka_1:9092 --from-beginning --topic Topic1
public class KafkaApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(KafkaApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		KafkaProducer producer = context.getBean(KafkaProducer.class);
		producer.send("test5");
		context.getBean(KafkaConsumer.class).latch.await(10, TimeUnit.SECONDS);
		context.close();
	}

}