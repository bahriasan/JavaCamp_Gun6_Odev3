package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerValidationService{
	void validate(Employer employer) throws Exception;

}
