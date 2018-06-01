package it.objectway.stage.demospringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import it.objectway.stage.demospringboot.controller.DemoController;
import it.objectway.stage.demospringboot.controller.PersistenceController;
import it.objectway.stage.demospringboot.dati.DipendentiDao;

@Import({ DemoController.class, PersistenceController.class })
public class ControllerConfig {

	@Bean
	public DipendentiDao dipDao() {
		return new DipendentiDao();
		
	}
}
