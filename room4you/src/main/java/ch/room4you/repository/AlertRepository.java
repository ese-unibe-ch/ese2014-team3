package ch.room4you.repository;


/**
 * Manages the database operations for the ad entity
 */
import org.springframework.data.jpa.repository.JpaRepository;
import ch.room4you.entity.Alert;
import ch.room4you.entity.User;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer> {

	List<Alert> findByUser(User user);	
	
}
