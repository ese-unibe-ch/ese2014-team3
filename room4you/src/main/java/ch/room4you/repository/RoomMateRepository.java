package ch.room4you.repository;


/**
 * Manages the database operations for the ad entity
 */
import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Image;
import ch.room4you.entity.RoomMate;
import ch.room4you.entity.User;

import java.util.List;

public interface RoomMateRepository extends JpaRepository<RoomMate, Integer> {

	List<RoomMate> findByAd(Ad ad);
	
}
