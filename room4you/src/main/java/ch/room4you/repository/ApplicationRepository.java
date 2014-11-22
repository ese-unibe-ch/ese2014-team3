package ch.room4you.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.Application;
import ch.room4you.entity.User;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{

	List<Application> findBySender(User sender);
	List<Application> findByRecipient(User recipient);

}
