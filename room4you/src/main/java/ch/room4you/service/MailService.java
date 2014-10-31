package ch.room4you.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pojo.MailMail;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.AlertRepository;


@Service("mailService")
public class MailService{
	
	@Autowired
	private AdRepository adRepository;
	
	@Autowired
	private AlertService alertService;
	
	@Autowired
	private AlertRepository alertRepository;
	

	@Scheduled(fixedDelay = 60000)
	public void sendMailAlertScheduler() throws AddressException, MessagingException{
		System.out.println("Sending mails");
		sendAlerts();
	}
    

 
    public void sendAlerts() throws AddressException, MessagingException{
    	List<MailMail> mails = alertService.findMatchingAds();
    	System.out.println("Number of Mails found: "+mails.size());
    	for(MailMail mail : mails){
    		System.out.println("Mail sent: "+ mail.getRecipients()
    				+ "");
    		
    		mail.sendMail();
    		
    	}
    	
	} 
          

}

