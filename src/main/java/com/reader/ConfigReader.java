package com.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.amqp.AmqpItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Config.BatchConfig;
import com.dao.Student;

@Configuration
public class ConfigReader {

	@Autowired
	private BatchConfig batchConfig;
	
	Logger log = LoggerFactory.getLogger(ConfigReader.class);

	
	@Bean
	public ItemReader<Student> readRMQ(){
		return new AmqpItemReader<>(batchConfig.rabbitTemplateRead());
	}
}
