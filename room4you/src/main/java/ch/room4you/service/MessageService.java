package ch.room4you.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Message;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.MessageRepository;
import ch.room4you.repository.UserRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private AdRepository adRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void save(Message message) {
		message.createTimestamp();
		String messageWithNewLines = message.getMessage().replaceAll("\n", "<br>");
		message.setMessage(messageWithNewLines);
		messageRepository.save(message);
	}
	
	public Message findOne(int id) {
		return messageRepository.findOne(id);
	}
	
	
	
	@Transactional
	public User findOneWithMessages(String name) {
		User user = userRepository.findByName(name);
		List<Message> messages = messageRepository.findByRecipientOrderByTimestampDesc(user);
		List<Message> sentMessages = messageRepository.findBySenderOrderByTimestampDesc(user);
		user.setMessages(messages);
		user.setSentMessages(sentMessages);
		return user;
	}
	
	@Transactional
	public List<Message> findNbrOfUnreadMessages(String name) {
		User user = userRepository.findByName(name);
		List<Message> messages = messageRepository.findByUnReadTrueAndRecipient(user);
		return messages;
	}
	
	
	public void delete(int id) {
		messageRepository.delete(id);
	}
	
	public void unMarkMessage(int id){
		Message message = messageRepository.findOne(id);
		message.setUnRead(false);
		messageRepository.save(message);
	}
	
	public void markMessage(int id){
		Message message = messageRepository.findOne(id);
		message.setUnRead(true);
		messageRepository.save(message);
	}
	
	public void deleteAllMessages(List<Message> messages){
		for(Message message : messages){
			messageRepository.delete(message.getId());
		}
		
	}
}