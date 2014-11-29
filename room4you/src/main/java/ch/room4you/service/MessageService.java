package ch.room4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Message;
import ch.room4you.entity.User;
import ch.room4you.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public void save(Message message) {
		message.createTimestamp();
		messageRepository.save(message);
	}
	
	public Message findOne(int id) {
		return messageRepository.findOne(id);
	}
	
	public List<Message> findAllMessagesBySenderAndAd(User sender, Ad ad) {
		return messageRepository.findBySenderAndMessageAdOrderByTimestampAsc(sender, ad);
	}
	
	
	public void delete(int id) {
		messageRepository.delete(id);
	}
}