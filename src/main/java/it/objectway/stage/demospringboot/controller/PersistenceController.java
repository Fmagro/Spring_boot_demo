package it.objectway.stage.demospringboot.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.objectway.stage.demospringboot.config.Dipendente;
import it.objectway.stage.demospringboot.config.DipendenteRepository;
import it.objectway.stage.demospringboot.dati.DipendentiDao;

@Controller
public class PersistenceController {

	private static Logger logger = LoggerFactory.getLogger(PersistenceController.class);

	@Autowired
	private DipendentiDao dd;

	@Autowired
	private DipendenteRepository dr;

	// @RequestMapping(value = "/Allemp", method = RequestMethod.GET)
	// public ModelAndView employeeIndex() {
	// List<Dipendente> list;
	// list = dd.getAllDipendenti();
	// ModelAndView model = new ModelAndView("allemp");
	// model.addObject("list", list);
	// logger.info("XXXXX {}", list);
	// return model;
	// }
	//
	@RequestMapping(value = "/EmployeeForm", method = RequestMethod.GET)
	public ModelAndView employeeForm() {
		return new ModelAndView("employeeForm", "dipendente", new Dipendente());
	}

	//
	//
	// @RequestMapping(value = "/EmployeeSearch", method = RequestMethod.POST)
	// public ModelAndView employeeSearch(@ModelAttribute("SpringJPA")Dipendente
	// dipendente, ModelMap model) {
	// List<Dipendente> list;
	//
	// list = dd.searchDipendenti(dipendente.getLastName(),
	// dipendente.getFirstName(), dipendente.getJobId());
	//
	// ModelAndView modell = new ModelAndView("allemp");
	// modell.addObject("list", list);
	// logger.debug("AAAAAAA {}", list);
	// return modell;
	// }
	//
	//
	@RequestMapping(value = "/EmployeeInsertForm", method = RequestMethod.GET)
	public ModelAndView employeeInsertForm() {

		return new ModelAndView("employeeInsertForm", "dipendente", new Dipendente());
	}
	//
	//
	// @RequestMapping(value = "/EmployeeInsert", method = RequestMethod.POST)
	// public String employeeInsert(@ModelAttribute Dipendente d, ModelMap model) {
	// d.setHireDate(new Date());
	// logger.debug("{}", d);
	// dd.insert(d);
	// return "index";
	// }
	//
	// @RequestMapping(value = "/EmployeeUpdateForm/{employee_id}", method =
	// RequestMethod.GET)
	// public ModelAndView employeeUpdateForm(@PathVariable int employee_id) {
	// Dipendente d = dd.findById(employee_id);
	// logger.debug("{}", d);
	// logger.debug("{}", System.identityHashCode(d));
	// return new ModelAndView("updateEmployeeForm", "dipendenteedit", d);
	//
	//
	// }
	// @RequestMapping(value = "/EmployeeUpdate", method = RequestMethod.POST)
	// public String employeeUpdate(@ModelAttribute Dipendente d, ModelMap model) {
	// logger.debug("{}", System.identityHashCode(d));
	// logger.debug("{}", d);
	// logger.debug("{}", model.get("dipendente"));
	//// dd.update(d, d.getEmployeeId());
	// dd.update2(d);
	//
	// return "index";
	// }
	// @RequestMapping(value = "/ShowEmployee/{employee_id}")
	// public ModelAndView showEmployee(@PathVariable int employee_id ) {
	// Dipendente d = dd.findById(employee_id);
	// return new ModelAndView("showEmployee", "dipendente", d);
	// }

	@RequestMapping(value = "/Allemp", method = RequestMethod.GET)
	public ModelAndView employeeIndex() {
		Iterable<Dipendente> list;
		list = dr.findAll();
		return new ModelAndView("allemp", "list", list);
	}

	//
	@RequestMapping(value = "/EmployeeSearch", method = RequestMethod.POST)
	public ModelAndView employeeSearch(@ModelAttribute("SpringJPA") Dipendente dipendente, ModelMap model) {
		List<Dipendente> list;
//		list = dr.findDipendenteByFirstNameOrLastNameOrJobId(dipendente.getFirstName(),
//				dipendente.getLastName(), dipendente.getJobId());
//
		list = dr.searchDipendenti(dipendente.getLastName(), dipendente.getFirstName(), dipendente.getJobId());
		return new ModelAndView("allemp", "list", list);
	}

	@RequestMapping(value = "/EmployeeInsert", method = RequestMethod.POST)
	public String employeeInsert(@ModelAttribute Dipendente d, ModelMap model) {
		d.setHireDate(new Date());
		dr.save(d);
		return "index";
	}

	@RequestMapping(value = "/EmployeeUpdateForm/{employee_id}", method = RequestMethod.GET)
	public ModelAndView employeeUpdateForm(@PathVariable int employee_id) throws Throwable {
		Optional<Dipendente> d = dr.findById((Integer) employee_id);
		return new ModelAndView("updateEmployeeForm", "dipendenteedit", d.orElseThrow((Supplier<Throwable>) ()-> new Exception("Argomento null")));
	}
	
	 @RequestMapping(value = "/EmployeeUpdate", method = RequestMethod.POST)
	 public String employeeUpdate(@ModelAttribute Dipendente d, ModelMap model) {
		 Optional<Dipendente> inserimento =  dr.findById(d.getEmployeeId());
//		 if(inserimento.isPresent()) {
//			 Dipendente app = inserimento.get();
//			 app.setLastName(d.getLastName());
//			 app.setFirstName(d.getFirstName());
//			 dr.save(app);
//		 }
		 
		 inserimento.ifPresent(new Consumer<Dipendente>() {
			 public void accept(Dipendente ins) {
				 ins.setFirstName(d.getFirstName());
				 ins.setLastName(d.getLastName());
				 dr.save(ins);
			 }
		 });
		 return "index";
	 }
	 
	 @RequestMapping(value = "/ShowEmployee/{employee_id}")
	 public ModelAndView showEmployee(@PathVariable int employee_id ) throws Throwable {
		 Optional<Dipendente> d = dr.findById(employee_id);
		 return new ModelAndView("showEmployee", "dipendente", d.orElseThrow((Supplier<Throwable>) ()-> new Exception("Argomento null")));
	 }

}
