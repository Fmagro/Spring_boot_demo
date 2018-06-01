package it.objectway.stage.demospringboot.dati;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.objectway.stage.demospringboot.config.Dipendente;


@Transactional
@Component
public class DipendentiDao {
	private static Logger logger = LoggerFactory.getLogger(DipendentiDao.class);
	@Autowired
	private EntityManager template;
	
    public void setTemplate(EntityManagerFactory emf) {  
        this.template = emf.createEntityManager();  
    }  
//    public void createDipendente(){  
//        Dipendente account = new Dipendente();  
//        template.persist(account);  
//    }  
    
//    public void updateBalance(int accountNumber,double newBalance){  
//        Account account = template.find(Account.class, accountNumber);  
//        if(account != null){  
//            account.setBalance(newBalance);  
//        }  
//        template.merge(account);  
//    }
    
    
//    public void deleteAccount(int accountNumber){  
//        Account account = template.find(Account.class, accountNumber);  
//        if(account != null)  
//            template.remove(account);  
//    }  
    
    public void insert(Dipendente d) {
//    	logger.debug("prima persist {}", d);
//    	template.persist(d);
//    	logger.debug("dopo persist {}", d);
    	template.persist(d);

    }
    
    public void update(Dipendente d, int id) {
    	Dipendente db = template.find(Dipendente.class, id);
    	
    	Query query = template.createQuery("update Dipendente set first_name = :firstName, last_name = :lastName where employee_id = :id");
    	
    	query.setParameter("firstName", d.getFirstName());
    	query.setParameter("lastName", d.getLastName());
    	query.setParameter("id", d.getEmployeeId());
    	template.getTransaction().begin();
    	query.executeUpdate();
    	template.getTransaction().commit();
    	template.flush();
    }
    
    public void update2(Dipendente d) {
    	Dipendente db = template.find(Dipendente.class, d.getEmployeeId());
    	
    	db.setFirstName(d.getFirstName());
    	db.setLastName(d.getLastName());

    	template.merge(db);

    }
    
    public List<Dipendente> getAllDipendenti(){  
        List<Dipendente> accounts = template.createQuery("select e from Dipendente e").getResultList();  
//        System.out.println(accounts.size());
//        System.out.println(accounts);
        return accounts;  
    } 
    
    public Dipendente findById(int id) {
    	Query query = template.createQuery("select e from Dipendente e where employee_id = :id");
    	
    	query.setParameter("id", id);
    	
    	List<Dipendente> list = query.getResultList();
    	
    	return list.get(0);
    }
    
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
