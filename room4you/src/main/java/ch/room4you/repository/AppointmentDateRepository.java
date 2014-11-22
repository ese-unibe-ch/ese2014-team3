package ch.room4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.AppointmentDate;

public interface AppointmentDateRepository extends JpaRepository<AppointmentDate, Integer> {

}
