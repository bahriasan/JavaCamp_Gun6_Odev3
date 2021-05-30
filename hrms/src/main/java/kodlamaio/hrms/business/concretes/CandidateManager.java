package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.CandidateValidationService;
import kodlamaio.hrms.core.utilities.validation.EmailValidation;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {
	private CandidateValidationService candidateValidationManager;
	private EmailValidation emailValidation;
	private CandidateDao candidateDao;
	
	@Autowired
	public CandidateManager(CandidateValidationService candidateValidationManager,
			EmailValidation emailValidation, CandidateDao candidateDao) {
		this.candidateValidationManager=candidateValidationManager;
		this.emailValidation=emailValidation;
		this.candidateDao=candidateDao;
	}

	@Override
	public void add(Candidate candidate) {
		
		boolean thrown=true;
		
		 try {
			 candidateValidationManager.validate(candidate);
			 emailValidation.validate(candidate); 
		 }
		 
		 catch(Exception e){
			 e.printStackTrace();
			 thrown=false;
		 }
		 
		 finally {
			 if(thrown) {
				 candidateDao.save(candidate); 
			 }
		 }
	}

	@Override
	public List<Candidate> getAll() {
		
		return candidateDao.findAll();
	}

	
	
	
}
