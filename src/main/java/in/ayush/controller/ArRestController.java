package in.ayush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ayush.binding.CitizenApp;
import in.ayush.service.ArService;

@RestController
public class ArRestController {
	
	@Autowired
	private ArService service;
	
	@PostMapping("/application")
	public ResponseEntity<String> createCitizenApplication(@RequestBody CitizenApp app){
		Integer appId = service.createApplcation(app);
		if(appId > 0) {
			return new ResponseEntity<>("Application Successful with Id: "+appId, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Citizen does not belong to New Jersey..!!", HttpStatus.BAD_REQUEST);
		}
	}

}
