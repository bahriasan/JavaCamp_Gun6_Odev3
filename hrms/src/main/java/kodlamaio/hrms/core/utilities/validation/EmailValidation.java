package kodlamaio.hrms.core.utilities.validation;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.result.ErrorResult;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class EmailValidation{

	//Simulation
	private boolean isEmailValidated=true;
	
	public void validate(User user) throws Exception {
		if(!isValidationEmailAnswered(user.getEmail()).isSuccess()) {
			throw new Exception(isValidationEmailAnswered(user.getEmail()).getMessage());
		}
	}
	
	
	private Result isValidationEmailAnswered(String email) {
		if(!isEmailValidated) {
			return new ErrorResult("ValidationEmailNotAnswered");
		}return new SuccessResult();
	}
}
