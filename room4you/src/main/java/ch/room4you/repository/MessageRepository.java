package ch.room4you.repository;


/**
 * Manages the database operations for the message entity
 */
import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Message;
import ch.room4you.entity.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByRecipientOrderByTimestampDesc(User recipient);
	
	List<Message> findBySenderOrderByTimestampDesc(User sender);
	
	List<Message> findByUnReadTrueAndRecipient(User recipient);
		
}
