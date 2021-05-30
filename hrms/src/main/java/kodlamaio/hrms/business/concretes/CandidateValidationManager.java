package kodlamaio.hrms.business.concretes;


import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateValidationService;
import kodlamaio.hrms.core.utilities.result.ErrorResult;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.core.utilities.validation.MernisValidation;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class CandidateValidationManager implements CandidateValidationService{
	
	private UserDao userDao;
	private CandidateDao candidateDao;
	private MernisValidation mernisValidation;
	
	public CandidateValidationManager(UserDao userDao, CandidateDao candidateDao, MernisValidation mernisValidation) {
		this.userDao=userDao;
		this.candidateDao=candidateDao;
		this.mernisValidation=mernisValidation;
	}
	
	@Override
	public void validate(Candidate candidate) throws Exception{
		
	Result[] results = new Result[] {isFirstNameNull(candidate.getFirstName()),isLastNameNull(candidate.getLastName()),
						isNationalityIsNull(candidate.getNationalityId()), isBirthDateNull(candidate.getBirthYear()),
						isEmailValid(candidate.getEmail()), isPasswordNull(candidate.getPassword()),
						isEmailDuplicated(candidate.getEmail()), isNationalityIdDuplicated(candidate.getNationalityId())
						
						};
	//,mernisValidation.validate(candidate) eklenecek
	
	for(Result result:results) {
		if(!result.isSuccess()) {
			throw new Exception(result.getMessage());
		}
	}
	
	}
	
	
	//FirstName Null
	private Result isFirstNameNull(String firstName) {
		if(firstName.equals(null) || firstName.isEmpty()) {
			return new ErrorResult("FirstNameNullError");
		} return new SuccessResult();
	}
	
	//LastName Null
	private Result isLastNameNull(String lastName) {
		if(lastName.equals(null) || lastName.isEmpty()) {
			return new ErrorResult("LastNameNullError");
		}return new SuccessResult();
	}
	
	//Nationality Id Null
	private Result isNationalityIsNull(String nationalityId) {
		if (nationalityId.equals(null) || nationalityId.isEmpty()) {
			return new ErrorResult("NationalityIdNullError");
		}return new SuccessResult();
	}
	
	//Birth Year Null
	private Result isBirthDateNull(int date) {
		//Buraya bir daha bak
		if(date==0) {
			return new ErrorResult("YearOfBirthNotValid");
		}return new SuccessResult();
	}
	
	//Email Regex
	private Result isEmailValid(String email) {
		String regexString= "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		if(!Pattern.compile(regexString,Pattern.CASE_INSENSITIVE).matcher(email).matches()) {
			return new ErrorResult("EmailNotValidError");
		}return new SuccessResult();
	}
	
	//Password Null
	private Result isPasswordNull(String password) {
		if(password.equals(null) || password.isEmpty()) {
			return new ErrorResult("PasswordNullError");
		}return  new SuccessResult();
	}
	
	//Password repeat will be added	
	
	//Email Duplication
	private Result isEmailDuplicated(String email) {
		for(User user:userDao.findAll()) {
			if(user.getEmail().equals(email)) {
				return new ErrorResult("EmailDuplicationError");
			}
		}return new SuccessResult();
	}
	
	
	//NationalityIdDuplication
	private Result isNationalityIdDuplicated(String nationalityId) {
		for(Candidate candidate:candidateDao.findAll()) {
			if(candidate.getNationalityId().equals(nationalityId)) {
				return new ErrorResult("NationalityIdDuplicationError");
			}
		}return new SuccessResult();
	}
	
	
}
