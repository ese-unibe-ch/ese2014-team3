package ch.room4you.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	List<Appointment> findByAppointmentAd(Ad appointmentAd);

}
