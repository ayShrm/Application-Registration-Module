package in.ayush.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.ayush.binding.CitizenApp;
import in.ayush.entity.CitizenAppEntity;
import in.ayush.repo.CitizenAppRepository;

@Service
public class ArServiceImpl implements ArService{
	
	@Autowired
	private CitizenAppRepository appRepo;

	@Override
	public Integer createApplcation(CitizenApp app) {
		
		String endPointUrl = "https://ssa-web-api.herokuapp.com/ssn/{ssn}";
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> resEntity = rt.getForEntity(endPointUrl, String.class, app.getSsn());
		String stateName = resEntity.getBody();
		if(stateName.equals("New Jersey")) {
			CitizenAppEntity entity = new CitizenAppEntity();
			BeanUtils.copyProperties(app, entity);
			entity.setStateName(stateName);
			CitizenAppEntity save = appRepo.save(entity);
			return save.getAppId();
		}
		return 0;
	}

}
