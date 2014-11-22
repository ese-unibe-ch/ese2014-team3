package ch.room4you.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ch.room4you.entity.Ad;
import ch.room4you.entity.Appointment;
import ch.room4you.entity.AppointmentDate;
import ch.room4you.entity.User;
import ch.room4you.repository.AppointmentRepository;
import ch.room4you.repository.UserRepository;

@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public Appointment findOne(int id) {
		return appointmentRepository.findOne(id);
	}


/*	public Appointment save(AppointmentDate date, AdForm adForm, Ad ad) {
		Appointment appointment = new Appointment();
		
		appointment.setAdPlacer(adForm.getUser());
		appointment.setAppointDate(date);
		appointment.setNmbrVisitors(adForm.getNmbrVisitors());
		appointment.setAd(ad);
		
		appointmentRepository.save(appointment); 
		return appointment;
	} */


	public void save(Appointment appointment) {
		appointmentRepository.save(appointment);
	}



}
