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
		return messageRepository.findDistinctBySenderOrRecipientAndMessageAdOrderByTimestampAsc(userSender, userRecipient, ad);
	}
	
	@Transactional
	public List<Message> findFirstSentMessageOfConversations(User userSender, User userRecipient) {
		List<Message> firstMessagesOfUser = new ArrayList<Message>();
		List<Ad> ads = adRepository.findAll();
		if(!ads.isEmpty()){
		for(Ad ad : ads){
			List<Message> mesSender = messageRepository.findDistinctBySenderAndMessageAdOrderByTimestampAsc(userSender, ad);
						
			if(!mesSender.isEmpty())
			firstMessagesOfUser.add(messageRepository.findDistinctBySenderAndMessageAdOrderByTimestampAsc(userSender, ad).get(0));
		
		}
	}
		return firstMessagesOfUser;
	}
	
	@Transactional
	public List<Message> findFirstReceivedMessageOfConversations(User userSender, User userRecipient) {
		List<Message> firstMessagesOfUser = new ArrayList<Message>();
		List<Ad> ads = adRepository.findAll();
		if(!ads.isEmpty()){
		for(Ad ad : ads){
			List<Message> mesRecipient = messageRepository.findDistinctByRecipientAndMessageAdOrderByTimestampAsc(userRecipient, ad);
			
			if(!mesRecipient.isEmpty())
				firstMessagesOfUser.add(messageRepository.findDistinctByRecipientAndMessageAdOrderByTimestampAsc(userRecipient, ad).get(0));
			}
		
		}
		return firstMessagesOfUser;
	}
	
	@Transactional
	public Message findFirstMessage(User userSender, User userRecipient, Ad ad) {
		return messageRepository.findDistinctTop1BySenderOrRecipientAndMessageAdOrderByTimestampAsc(userSender, userRecipient, ad);
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