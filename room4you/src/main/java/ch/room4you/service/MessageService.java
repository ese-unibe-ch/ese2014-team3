package ch.room4you.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Message;
import ch.room4you.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public void save(Message message) {
		messageRepository.save(message);
	}
	
	public Message findOne(int id) {
		return messageRepository.findOne(id);
	}
	
	public void delete(int id) {
		messageRepository.delete(id);
	}
}