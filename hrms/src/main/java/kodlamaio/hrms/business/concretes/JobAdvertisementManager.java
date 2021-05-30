package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{

	private JobAdvertisementDao jobAdvertisementDao;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		this.jobAdvertisementDao=jobAdvertisementDao;
	}
	
	
	@Override
	public void add(JobAdvertisement jobAdvertisement) {
		jobAdvertisementDao.save(jobAdvertisement);
	}


	@Override
	public List<JobAdvertisement> getByIsActiveTrue() {
		return jobAdvertisementDao.getByIsActiveTrue();
	}

	
	@Override
	public List<JobAdvertisement> getByIsActiveTrueOrderByExpireDate() {
		return jobAdvertisementDao.getByIsActiveTrueOrderByExpireDate();
	}
	
	

	@Override
	public List<JobAdvertisement> getByIsActiveTrueAndEmployer_CompanyName(String companyName) {
		return jobAdvertisementDao.getByIsActiveTrueAndEmployer_CompanyName(companyName);
	}


	@Override
	public void updateIsActiveFalse(int id) {
		if(!jobAdvertisementDao.getById(id).isActive()) {
			jobAdvertisementDao.getById(id).setIsActive(false);
		}
		
	}



	

}
