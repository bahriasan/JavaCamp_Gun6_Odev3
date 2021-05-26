package kodlamaio.hrms.core.utilities.email;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.result.ErrorResult;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class HrmsValidation {
	
	private boolean hrmsValidation=true;

	public void validate(User user) throws Exception{
		if(!isHrmsValidationDone(user).isSuccess()) {
			throw new Exception(isHrmsValidationDone(user).getMessage());
		}
	}
	
	private Result isHrmsValidationDone(User user) {
		if(!hrmsValidation) {
			return new ErrorResult("HRMSValidationFail");
		}return new SuccessResult();
	}

}
