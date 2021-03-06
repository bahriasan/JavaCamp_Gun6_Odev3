package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {
	
	private EmployerService employerManager;
	
	@Autowired
	public EmployersController(EmployerService employerManager) {
		this.employerManager=employerManager;
	}
	
	@PostMapping("/add")
	public void add(Employer employer) {
		employerManager.add(employer);
	}
	
	
	@GetMapping("/getAll")
	public List<Employer> getAll(){
		return employerManager.getAll();
	}
	
}
