package ch.room4you.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Appointment;
import ch.room4you.entity.Application;
import ch.room4you.entity.Message;
import ch.room4you.repository.ApplicationRepository;

@Service
public class ApplicationService {
	
	@Autowired 
	private ApplicationRepository applicationRepository;

	/*public void save(Message message, Appointment appointment) {
				
			Application application = new Application();
			application.setAppointment(appointment);
			application.setMessage(message);
			applicationRepository.save(application);
			
	} */

	public void save(Application application) {
		applicationRepository.save(application);
		
	}

	public Application findOne(int id) {
		return applicationRepository.findOne(id);
	}

}
