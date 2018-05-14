package com.hhit.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Learn application.
 *
 * @author GeekYe
 */
@SpringBootApplication
@MapperScan("com.hhit.learn.mapper")
public class LearnApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {

		SpringApplication.run(LearnApplication.class, args);

	}
}
