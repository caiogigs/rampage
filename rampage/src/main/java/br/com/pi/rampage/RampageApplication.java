package br.com.pi.rampage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.pi.rampage.telas.Terminal;

@SpringBootApplication
public class RampageApplication {

	public static void main(String[] args) {
		SpringApplication.run(RampageApplication.class, args);
		Terminal terminal = new Terminal();
		terminal.includeUser();
	}

}
