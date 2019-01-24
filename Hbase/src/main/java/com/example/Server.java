package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  
@ComponentScan  
@EnableAutoConfiguration 
public class Server {
	// 启动springboot
	public static void main(String[] args) {
		System.out.print("springboot 启动啦啦啦啦啦啦啦啦啦啦啦");
		SpringApplication.run(Server.class, args);
		System.out.print("springboot 启动啦啦啦啦啦啦啦啦啦啦啦");
	}
}
