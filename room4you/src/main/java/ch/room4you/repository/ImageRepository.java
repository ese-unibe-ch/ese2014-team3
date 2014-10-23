package ch.room4you.repository;


/**
 * Manages the database operations for the image entity
 */
import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	List<Image> findByAd(Ad ad);
	
}
