package com.onlineshop.shopping_application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
			info = @Info(title = "Product Service", version = "v1"),
		servers = {
				@Server(url = "http://127.0.0.1:8765", description = "Gateway URL")
		}
)
@SpringBootApplication
public class OnlineShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingApplication.class, args);
	}
	//docker run -p 3306:3306 --name mysqlinstance -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=onlineshop -d mysql}
}


