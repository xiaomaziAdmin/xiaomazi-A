package com.xiaomazi.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication //这个注解等同于下面的三个注解

//@SpringBootConfiguration //这里面嵌套了一个 @Configuration 就说明他是个配置类
//@EnableAutoConfiguration
//@ComponentScan
@MapperScan(basePackages = "com.xiaomazi.springboot.mapper")
public class Ch01SpringbootDay01Application implements CommandLineRunner, ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(Ch01SpringbootDay01Application.class, args);
	}


	/**
	 * 回调方法
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("-----1");
	}

	/**
	 * 回调方法
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		System.out.println("-----2");
	}
}
