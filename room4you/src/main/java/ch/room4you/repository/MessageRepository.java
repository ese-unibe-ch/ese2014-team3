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

	List<Message> findByRecipientOrderByTimestampAsc(User recipient);
	
	List<Message> findBySenderOrderByTimestampAsc(User sender);
		
	List<Message> findDistinctBySenderAndMessageAdOrderByTimestampAsc(User sender, Ad ad);
	
	List<Message> findDistinctByRecipientAndMessageAdOrderByTimestampAsc(User recipient, Ad ad);
	
	List<Message> findDistinctBySenderOrRecipientAndMessageAdOrderByTimestampAsc(User sender, User recipient, Ad ad);
	
	List<Message> findDisctinctBySenderOrRecipient(User sender, User recipient);	
		
	Message findDistinctTop1BySenderOrRecipientAndMessageAdOrderByTimestampAsc(User sender, User recipient, Ad ad);
	
	List<Message> findByUnReadTrueAndSender(User sender);
	
	List<Message> findByUnReadTrueAndRecipient(User recipient);
		
}
