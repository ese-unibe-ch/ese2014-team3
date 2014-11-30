package ch.room4you.repository;


/**
 * Manages the database operations for the message entity
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Message;
import ch.room4you.entity.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByRecipient(User recipient);
	List<Message> findBySender(User sender);
	
	
	List<Message> findBySenderOrRecipientAndMessageAdOrderByTimestampAsc(User sender, User recipient, Ad ad);
	
	
//	 @Query("SELECT message "
//		 		+ "FROM Message message "
//		 		+ "WHERE "
//		 		+ "message.sender == :sender "
//		 		+ "Or "
//		 		+ "message.recipient == :recipient "
//		 		+ "AND "
//		 		+ "message.ad == :ad ")
//		 public List<Ad> findAdsForChat(
//				 @Param("sender") int sender, 
//				 @Param("recipient") int recipient, 
//				 @Param("ad") int ad
//				 );
		
}
