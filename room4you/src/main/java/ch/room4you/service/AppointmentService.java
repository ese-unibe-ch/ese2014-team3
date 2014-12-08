package ch.room4you.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Appointment;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.AppointmentRepository;
import ch.room4you.repository.FavCandidatesRepository;
import ch.room4you.repository.UserRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FavCandidatesRepository candidatesRepository;

	@Autowired
	private AdRepository adRepository;

	public Appointment findOne(int id) {
		return appointmentRepository.findOne(id);
	}


	public void save(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	@Transactional
	public void addVisitor(int appointId, String userName) {
		Appointment appointment = appointmentRepository.findOne(appointId);
		User user = userRepository.findByName(userName);

		if (!isVisitor(user, appointment)) {
			if (appointment.getNmbrVisitors() > 0) {

				List<Appointment> userAppointments = user.getAppointments();
				// userAppointments.remove(appointment);

				List<User> visitors = appointment.getVisitors();
				visitors.add(user);
				appointment.decrementNmbrVisitors();
				appointment.setVisitors(visitors);
				appointmentRepository.save(appointment);
				
				userAppointments.add(appointment);
				user.setAppointment(userAppointments);
				userRepository.save(user);
			}
		}
	}

	public boolean isVisitor(User user, Appointment appointment) {
		List<User> visitors = appointment.getVisitors();

		for (User visitor : visitors) {
			if (visitor.getId() == user.getId()) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	public List<Appointment> findByAd(int id) {
		Ad ad = adRepository.findOne(id);
		List<Appointment> appointments = appointmentRepository
				.findByAppointmentAd(ad);
		return appointments;
	}

	@Transactional
	public void delteAppointment(int appointId) {
		appointmentRepository.delete(appointId);
	}

}
