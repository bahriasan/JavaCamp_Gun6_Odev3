package kodlamaio.hrms.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.entities.concretes.City;

@RestController
@RequestMapping("/api/city")
public class CityController {
	private CityService cityService;
	
	
	public CityController(CityService cityService) {
		this.cityService=cityService;
	}
	
	@PostMapping("add")
	public void add(City city) {
		cityService.add(city);
	}
	

		
	
	
}
