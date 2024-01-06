package org.example;

import org.apache.pulsar.client.api.SubscriptionType;
import org.apache.pulsar.common.schema.SchemaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.pulsar.listener.AckMode;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@SpringBootApplication
public class SpringPulsarApp {

	private static final Logger logger = LoggerFactory.getLogger(SpringPulsarApp.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringPulsarApp.class, args);
	}
	@Bean
	ApplicationRunner runner1(PulsarTemplate<User> pulsarTemplate) {

		String topic1 = "pulsar-listener-demo-topic";
		return args -> {
			while (true) {
				User user = new User(randomAlphanumeric(20), randomAlphanumeric(10), 20);
				pulsarTemplate.send(topic1, user);
			}
		};
	}

	private int counter = 0;
	@PulsarListener(
			subscriptionName = "user-topic-subscription",
			topics = "pulsar-listener-demo-topic",
			subscriptionType = SubscriptionType.Exclusive,
			schemaType = SchemaType.JSON,
			ackMode = AckMode.BATCH
	)
	public void userTopicListener(User user) {
		counter++;

		if (counter % 100 == 0) {
			logger.info("Received " + counter + " messages. Last user=" + user);
		}
	}

}
