package ch.room4you.repository;


/**
 * Manages the database operations for the ad entity
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.room4you.entity.Ad;
import ch.room4you.entity.User;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {

	List<Ad> findByUser(User user);
	
	List<Ad> findByCityIgnoreCase(String city);
	
      /**
	  * Finds an ad by using the city as a search criteria.
	  * @param city
	  * @return  A list of ads whose city is an exact match with the given city.
	  *          If no persons is found, this method returns an empty list.
	  */
	 @Query("SELECT ad FROM Ad ad WHERE LOWER(ad.city) = LOWER(:city)")
	 public List<Ad> findAdsWithFormCriteria(@Param("city") String city);
	
}
