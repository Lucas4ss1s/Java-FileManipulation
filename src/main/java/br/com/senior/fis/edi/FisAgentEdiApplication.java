package br.com.senior.fis.edi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FisAgentEdiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FisAgentEdiApplication.class, args);

	}

}
