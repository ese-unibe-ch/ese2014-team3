package ch.room4you.repository;


/**
 * Manages the database operations for the room mate entity
 */
import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.Ad;
import ch.room4you.entity.RoomMate;

import java.util.List;

public interface RoomMateRepository extends JpaRepository<RoomMate, Integer> {

	List<RoomMate> findByAd(Ad ad);
	
}
