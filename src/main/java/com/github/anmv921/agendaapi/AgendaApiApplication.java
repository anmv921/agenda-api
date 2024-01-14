package com.github.anmv921.agendaapi;

import com.github.anmv921.agendaapi.model.entity.Contacto;
import com.github.anmv921.agendaapi.model.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgendaApiApplication {

	@Bean
	public CommandLineRunner commandLineRunner(
			@Autowired ContactoRepository repository ) {
			return args -> {
				Contacto contacto = new Contacto();
				contacto.setNome("Soprano");
				contacto.setEmail("soprano@email.br");
				contacto.setFavorito(false);
				repository.save(contacto);
			};
	}

	public static void main(String[] args) {
		SpringApplication.run(AgendaApiApplication.class, args);
	}

}
