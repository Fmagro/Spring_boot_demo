package it.objectway.stage.demospringboot.config;

import java.util.List;

public interface DipendenteRepositoryCustom {

	
    public List<Dipendente> searchDipendenti(String cognome, String nome, String jobId);
}
