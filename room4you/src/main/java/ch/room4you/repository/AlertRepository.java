package ch.room4you.repository;


/**
 * Manages the database operations for the ad entity
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Alert;
import ch.room4you.entity.User;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer> {

	List<Alert> findByUser(User user);
	
      /**
	  * Finds an ad by using the city as a search criteria.
	  * @param city
      * @param searchTextNbrRoomsMax 
      * @param searchTextNbrRoomsMin 
      * @param searchTextNbrRoomMatesMax 
      * @param searchTextNbrRoomMatesMin 
	  * @return  A list of ads whose city is an exact match with the given city.
	  *          If no persons is found, this method returns an empty list.
	  */
	 @Query("SELECT ad "
	 		+ "FROM Ad ad "
	 		+ "WHERE "
	 		+ "LOWER(ad.city) LIKE LOWER(:city) "
	 		+ "AND "
	 		+ "ad.zip LIKE :zip "
	 		+ "AND "
	 		+ "ad.rentPerMonth BETWEEN :rentPerMonthMin AND :rentPerMonthMax "
	 		+ "AND "
	 		+ "ad.nbrRoomsMates BETWEEN :roomMatesMin AND :roomMatesMax "
	 		+ "AND "
	 		+ "ad.nbrRooms BETWEEN :roomsMin AND :roomsMax "
	 		+ "AND "
	 		+ "ad.sharedApartment = :sharedApartment")
	 public List<Ad> findAdsWithFormCriteria(
			 @Param("city") String city, 
			 @Param("zip") String zip, 
			 @Param("rentPerMonthMin") int rentPerMonthMin,
			 @Param("rentPerMonthMax") int rentPerMonthMax, 
			 @Param("roomMatesMin") int searchTextNbrRoomMatesMin, 
			 @Param("roomMatesMax") int searchTextNbrRoomMatesMax, 
			 @Param("roomsMin") float searchTextNbrRoomsMin, 
			 @Param("roomsMax")float searchTextNbrRoomsMax, 
			 @Param("sharedApartment") boolean sharedApartment
			 );
	
}
