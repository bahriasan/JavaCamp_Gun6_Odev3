package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.result.ErrorResult;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {
	
	private JobPositionDao jobPositionDao; 
	
	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao){
		this.jobPositionDao=jobPositionDao;
	}
	
	@Override
	public Result add(JobPosition jobPosition) {
		for (JobPosition job:jobPositionDao.findAll()) {
			if(job.equals(jobPosition)) {
				return new ErrorResult("JobPositionDuplicationError");
			}
		}
		jobPositionDao.save(jobPosition);
		return new SuccessResult(jobPosition.getJobTitle()+ " İş Listesine Eklendi.");
	}
	
	
	@Override
	public List<JobPosition> getAll() {
		return this.jobPositionDao.findAll();
	}

}
