package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.entities.concretes.Candidate;

public interface CandidateService {
	void add(Candidate candidate);
	
	List<Candidate> getAll();
}
