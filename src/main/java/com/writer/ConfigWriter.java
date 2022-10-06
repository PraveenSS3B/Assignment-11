package com.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.dao.Student;

@Configuration
public class ConfigWriter {

	Logger log = LoggerFactory.getLogger(ConfigWriter.class);

	@Bean
	public FlatFileItemWriter<Student> fromRMQWriter() {
		FlatFileItemWriter<Student> writer = new FlatFileItemWriter<Student>();
		writer.setResource(new FileSystemResource(
				System.getProperty("user.dir") + "/src/main/resources/output/student.csv"));
		DelimitedLineAggregator<Student> delimitedLineAggregator = new DelimitedLineAggregator<Student>();
		delimitedLineAggregator.setDelimiter(",");

		BeanWrapperFieldExtractor<Student> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<Student>();
		beanWrapperFieldExtractor.setNames(new String[] { "id", "classRoom", "gender", "marks", "name" });
		delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);

		writer.setLineAggregator(delimitedLineAggregator);
		log.trace("Writing into student.csv from RMQ....");
		return writer;
	}
}
