package it.objectway.stage.demospringboot.config;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

public class DipendenteRepositoryImpl implements DipendenteRepositoryCustom{

	@Autowired
	private EntityManager template;
	
    public List<Dipendente> searchDipendenti(String cognome, String nome, String jobId) {
		List<Dipendente> ld = new LinkedList<Dipendente>();
		Dipendente d;
		String prepQuery = "select e from Dipendente e WHERE 1 = 1";
		if (cognome != null && !cognome.isEmpty()) {
			prepQuery += "AND last_name = :lastName ";
		}

		if (nome != null && !nome.isEmpty()) {
			prepQuery += "AND first_name = :firstName ";
		}

		if (jobId != null && !jobId.isEmpty()) {
			prepQuery += "AND job_id = :jobId ";
		}
		
		Query query = template.createQuery(prepQuery);
		
		if (cognome != null && !cognome.isEmpty()) {
			query.setParameter("lastName", cognome);
		}

		if (nome != null && !nome.isEmpty()) {
			query.setParameter("firstName", nome);
		}

		if (jobId != null && !jobId.isEmpty()) {
			query.setParameter("jobId", jobId);
		}
		
		List<Dipendente> results = query.getResultList();
    	return results;
    }
}
