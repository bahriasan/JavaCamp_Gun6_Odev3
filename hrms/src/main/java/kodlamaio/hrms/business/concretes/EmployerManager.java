package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.EmployerValidationService;
import kodlamaio.hrms.core.utilities.email.EmailValidation;
import kodlamaio.hrms.core.utilities.email.HrmsValidation;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {
	
	private EmployerValidationService employerValidationManager;
	private EmployerDao employerDao;
	private EmailValidation emailValidation;
	private HrmsValidation hrmsValidation;
	
	@Autowired
	public EmployerManager(EmployerValidationService employerValidationManager, EmployerDao employerDao, 
			EmailValidation emailValidation, HrmsValidation hrmsValidation) {
		this.employerValidationManager=employerValidationManager;
		this.employerDao=employerDao;
		this.emailValidation=emailValidation;
		this.hrmsValidation=hrmsValidation;
	}
	
	@Override
	public void add(Employer employer) {
		
		boolean thrown=true;
		
		try {
			employerValidationManager.validate(employer);
			emailValidation.validate(employer);
			hrmsValidation.validate(employer);
		}
		catch(Exception e){
			e.printStackTrace();
			thrown=false;
		}
		finally {
			if(thrown) {
			employerDao.save(employer);
			}
		}
		
	}

	@Override
	public List<Employer> getAll() {
		
		return employerDao.findAll();
	}

}
