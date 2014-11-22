package ch.room4you.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ch.room4you.entity.AppointmentDate;
import ch.room4you.repository.AppointmentDateRepository;

@Service
public class AppointmentDateService {
	
	@Autowired
	AppointmentDateRepository dateRepository;

	/*public AppointmentDate save(AdForm adForm) {
		
		AppointmentDate date = new AppointmentDate();
		
		date.setAppointDate(adForm.getDate());
		date.setStartTime(adForm.getStartTime());
		date.setEndTime(adForm.getEndTime());
		
		System.out.println(date);
		
		dateRepository.save(date);
		
		return date;
	} */

	public void save(AppointmentDate date) {
		dateRepository.save(date);
	}
	

}
