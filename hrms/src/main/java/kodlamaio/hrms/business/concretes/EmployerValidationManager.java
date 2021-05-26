package kodlamaio.hrms.business.concretes;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerValidationService;
import kodlamaio.hrms.core.utilities.result.ErrorResult;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class EmployerValidationManager implements EmployerValidationService{
	private UserDao userDao;
	
	
	@Autowired
	EmployerValidationManager(UserDao userDao){
		this.userDao=userDao;
	}
	

	@Override
	public void validate(Employer employer) throws Exception{
		Result[] results=new Result[] {isCompanyNameValid(employer.getCompanyName()),isWebAddressValid(employer.getWebAddress()),
				isEmailValid(employer.getEmail()),isPhoneNumberValid(employer.getPhoneNumber()),isPasswordValid(employer.getPassword()),
				isEmailDuplicated(employer.getEmail())};
	
		for (Result result:results) {
			if(!result.isSuccess()) {
				throw new Exception(result.getMessage());
			}
		}
		
	}
			
	
	
	
	private Result isCompanyNameValid(String companyName) {
		if(companyName.equals(null)||companyName.isEmpty()) {
			return new ErrorResult("CompanyNameNullError");
		}return new SuccessResult();
	}
	
	private Result isWebAddressValid(String webAddress) {
		if(webAddress.equals(null)||webAddress.isEmpty()) {
			return new ErrorResult("WebAddressResultNullError");
		}return new SuccessResult();
	}
	
	private Result isEmailValid(String email) {
		if(email.equals(null)||email.isEmpty()) {
			return new ErrorResult("EmailNullError");
		}return new SuccessResult();
		// if webadress domain içermeli şartı eklenecek
	}
	
	private Result isPhoneNumberValid(String phoneNumber) {
		if(phoneNumber.equals(null)||phoneNumber.isEmpty()) {
			return new ErrorResult("PhoneNumberNotValid");
		}return new SuccessResult();
	}
	
	private Result isPasswordValid(String password) {
		if(password.equals(null)||password.isEmpty()) {
			return new ErrorResult("PasswordNotValid");
		}return new SuccessResult();
	}
	
	//Password repeat will be added
	
	private Result isEmailDuplicated(String email) {
		for(User user:userDao.findAll()) {
			if(user.getEmail().equals(email)) {
				return new ErrorResult("EmailDuplicateError");
			}
		}return new SuccessResult();
	}



}
