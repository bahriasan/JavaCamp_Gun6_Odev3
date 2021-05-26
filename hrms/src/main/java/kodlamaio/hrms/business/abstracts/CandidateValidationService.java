package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.Candidate;

public interface CandidateValidationService {
	void validate(Candidate candidate) throws Exception;
}
