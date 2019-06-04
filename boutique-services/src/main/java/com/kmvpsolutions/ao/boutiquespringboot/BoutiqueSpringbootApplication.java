package com.kmvpsolutions.ao.boutiquespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class BoutiqueSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoutiqueSpringbootApplication.class, args);
	}

}
