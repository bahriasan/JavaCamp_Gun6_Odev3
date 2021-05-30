package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementController {
	private JobAdvertisementService jobAdvertisementService;
	
	
	@Autowired
	public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
		this.jobAdvertisementService=jobAdvertisementService;
	}
	
	
	@PostMapping("/add")
	public void add(JobAdvertisement jobAdvertisement) {
		jobAdvertisementService.add(jobAdvertisement);
	}
	
	@GetMapping("/getByIsActiveTrue")
	public List<JobAdvertisement> isActiveTrue(){
		return jobAdvertisementService.getByIsActiveTrue();
	}
	
	@GetMapping("/getByIsActiveTrueOrderByExpireDate")
	public List<JobAdvertisement> isActiveTrueOrderByExpireDate(){
		return jobAdvertisementService.getByIsActiveTrueOrderByExpireDate();
	}
	
	@GetMapping("/getByIsActiveTrueAndEmployer_CompanyName")
	public List<JobAdvertisement> isActiveTrueAndCompanyName(String companyName){
		return jobAdvertisementService.getByIsActiveTrueAndEmployer_CompanyName(companyName);
	}
	
	@PostMapping("/passiveJobAdvertisement")
	public void passiveJobAdvertisement(int id) {
		jobAdvertisementService.updateIsActiveFalse(id);
	}
	
	
}
