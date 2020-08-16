package br.com.dirceus.meudoc;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MeuDocApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuDocApplication.class, args);
	}
	
	 @Bean
	 public ModelMapper modelMapper() {
	        return new ModelMapper();
	 }

}
