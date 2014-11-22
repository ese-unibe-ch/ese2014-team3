package ch.room4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
