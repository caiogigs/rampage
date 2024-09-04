package br.com.pi.rampage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.pi.rampage.view.Terminal;

//Classe main do projeto
@SpringBootApplication
public class RampageApplication implements CommandLineRunner{

	@Autowired
    private Terminal terminal;

	public static void main(String[] args) {
		SpringApplication.run(RampageApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
		terminal.start();
    }

}
