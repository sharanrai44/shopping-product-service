package com.onlineshop.shopping_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingApplication.class, args);
	}
	//docker run -p 3306:3306 --name mysqlinstance -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=onlineshop -d mysql}
}


