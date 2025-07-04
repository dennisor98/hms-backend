package com.openmarket.hms.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
	 @Bean
	    Jackson2JsonMessageConverter jsonMessageConverter() {
	        return new Jackson2JsonMessageConverter();
	    }
	 
 
	    @Bean
	    TopicExchange patientexchange() {
	        return new TopicExchange("patientExchange");
	    }
	    
	    
	    @Bean
	    Queue triageQueue() {
	        return new Queue("triageQueue", true);
	    }
	   

	    @Bean
	    Binding bindingTriageQueue(
	    		@Qualifier("triageQueue") Queue triageQueue,
	            TopicExchange exchange) {
	        return BindingBuilder.bind(triageQueue).to(exchange).with("patient.nextTriage");
	    }
	 
}
