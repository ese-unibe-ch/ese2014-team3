package ch.room4you.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pojo.MailMail;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.AlertRepository;


@Service("mailService")
public class MailService{
	
	  static Logger log = Logger.getLogger(
              MailService.class.getName());
	
	@Autowired
	private AdRepository adRepository;
	
	@Autowired
	private AlertService alertService;
	
	@Autowired
	private AlertRepository alertRepository;
	

	@Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
	public void sendMailAlertScheduler() throws AddressException, MessagingException{
		log.info("sending mails...");
		sendAlerts();
	}
    

 
    public void sendAlerts() throws AddressException, MessagingException{
    	List<MailMail> mails = alertService.findMatchingAds();
    	for(MailMail mail : mails){
    		mail.sendMail();
    		log.info("Mail sent to: "+ mail.getRecipients());
    		
    	}
    	
	} 
          

}

