package cn.edu.zju.rushrushrush.roadmonitorbackend.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
	
	@Autowired
	private KafkaProperties kafkaProperties;

	@Autowired
	private KafkaTemplate<String, String> template;

	public void send(String foo) {
		this.template.send(this.kafkaProperties.getTopic(), foo);
	}
}