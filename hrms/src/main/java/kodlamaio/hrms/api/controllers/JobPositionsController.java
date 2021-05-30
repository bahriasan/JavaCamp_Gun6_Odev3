package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.entities.concretes.JobPosition;

@RestController
@RequestMapping("/api/jobPositions")
public class JobPositionsController {
	
	private JobPositionService jobPositionManager;
	
	@Autowired
	public JobPositionsController(JobPositionService jobPositionManager) {
		super();
		this.jobPositionManager = jobPositionManager;
	}
	
	@PostMapping("/add")
	public void add(JobPosition jobPosition) {
		this.jobPositionManager.add(jobPosition);
	}
	
	@GetMapping("/getAll")
	public List<JobPosition> getAll(){
		return jobPositionManager.getAll();
	}

}
