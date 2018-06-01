package it.objectway.stage.demospringboot.config;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface DipendenteRepository extends CrudRepository<Dipendente, Integer>, DipendenteRepositoryCustom{

	
	public List<Dipendente> findDipendenteByFirstNameOrLastNameOrJobId(String firstName, String lastName, String jobId);
}
