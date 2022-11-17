package com.ssafy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(basePackages = {"com.ssafy.**.dao"})
@ComponentScan(basePackages = {"com.ssafy"})
public class Pjt06Bug040808Application {

	public static void main(String[] args) {
		SpringApplication.run(Pjt06Bug040808Application.class, args);
	}

}
