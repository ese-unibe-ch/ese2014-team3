package ch.room4you.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Message;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private AdRepository adRepository;
	
	public void save(Message message) {
		message.createTimestamp();
		messageRepository.save(message);
	}
	
	public Message findOne(int id) {
		return messageRepository.findOne(id);
	}
	
	@Transactional
	public List<Message> findAllMessagesBySenderAndAd(User userSender, User userRecipient, Ad ad) {
		return messageRepository.findBySenderOrRecipientAndMessageAdOrderByTimestampAsc(userSender, userRecipient, ad);
	}
	
	@Transactional
	public List<Message> findFirstMessageOfConversations(User userSender, User userRecipient) {
		List<Message> firstMessagesOfUser = new ArrayList<Message>();
		List<Ad> ads = adRepository.findAll();
		System.out.println("Ads size: "+ads.size());
		if(!ads.isEmpty()){
		for(Ad ad : ads){
			List<Message> mes = messageRepository.findBySenderOrRecipientAndMessageAdOrderByTimestampAsc(userSender, userRecipient, ad);
			if(!mes.isEmpty())
			firstMessagesOfUser.add(messageRepository.findBySenderOrRecipientAndMessageAdOrderByTimestampAsc(userSender, userRecipient, ad).get(0));
			
		}
		}
		System.out.println("MessageSize: "+firstMessagesOfUser.size());
		return firstMessagesOfUser;
	}
	
	@Transactional
	public Message findFirstMessage(User userSender, User userRecipient, Ad ad) {
		return messageRepository.findTop1BySenderOrRecipientAndMessageAdOrderByTimestampAsc(userSender, userRecipient, ad);
	}
	
	
	public void delete(int id) {
		messageRepository.delete(id);
	}
	
	public void deleteAllMessages(List<Message> messages){
		for(Message message : messages){
			messageRepository.delete(message.getId());
		}
		
	}
}