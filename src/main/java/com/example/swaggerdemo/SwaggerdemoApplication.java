package com.example.swaggerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwaggerdemoApplication
{
	// swagger的ui界面uri为 localhost:8080/swagger-ui.html
	// 生成静态文档的代码在测试里
	public static void main(String[] args)
	{
		SpringApplication.run(SwaggerdemoApplication.class, args);
	}

}
