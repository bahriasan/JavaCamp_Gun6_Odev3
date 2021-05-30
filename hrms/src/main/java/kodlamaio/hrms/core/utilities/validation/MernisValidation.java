package kodlamaio.hrms.core.utilities.validation;

import java.rmi.RemoteException;
import java.util.Locale;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.result.ErrorResult;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.entities.concretes.Candidate;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisValidation{
	
	
	//Mernis Validation

	
//	public Result validate(Candidate candidate){
//		KPSPublicSoapProxy kpsPublic = new KPSPublicSoapProxy();
//		boolean result = false;
//		
//		try{
//			result=kpsPublic.TCKimlikNoDogrula(Long.parseLong(candidate.getNationalityId()),candidate.getFirstName().toUpperCase(new Locale("tr")),candidate.getLastName().toUpperCase(new Locale("tr")),candidate.getBirthYear());
//			System.out.println(result);
//		} catch(RemoteException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(result);
//		if(result) {return new SuccessResult();}
//			return new ErrorResult("MernisValidationError");
//			
//	}	
	
	public Result validate(Candidate candidate)throws RemoteException {
		KPSPublicSoapProxy kpsPublic = new KPSPublicSoapProxy();
		boolean result = kpsPublic.TCKimlikNoDogrula(
				Long.parseLong(candidate.getNationalityId()), 
				candidate.getFirstName().toUpperCase(new Locale("tr")), 
				candidate.getLastName().toUpperCase(new Locale("tr")),
				candidate.getBirthYear());
		if(result) {
			return new SuccessResult();
		} return new ErrorResult("MernisValidationError");
	
	}
}	
