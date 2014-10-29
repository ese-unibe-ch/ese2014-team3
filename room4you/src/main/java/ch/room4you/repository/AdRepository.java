package ch.room4you.repository;


/**
 * Manages the database operations for the ad entity
 */
import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.Ad;
import ch.room4you.entity.User;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {

	List<Ad> findByUser(User user);
	
	List<Ad> findByCity(String City);
	
}
