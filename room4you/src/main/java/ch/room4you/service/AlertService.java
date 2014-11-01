package ch.room4you.service;

/**
 * Database operation service for roomMateRepository interface
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.MailMail;
import ch.room4you.entity.Ad;
import ch.room4you.entity.Alert;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.AlertRepository;
import ch.room4you.repository.ImageRepository;
import ch.room4you.repository.UserRepository;

@Service
public class AlertService{
	
	private final String LOCALHOSTNAME = "http://localhost:8080";
	private final String HOSTNAMEPRODUCTION = "http://localhost:8080";
	
	@Autowired
	private AdRepository adRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private AlertRepository alertRepository;
	
	@Autowired
	private AdRepository adService;
	
	
	/**
	 * Saves the ad in the database
	 * @param ad
	 * @param name
	 */
	public void save(Alert alert, String name) {
		User user = userRepository.findByName(name);
		alert.setUser(user);
		alertRepository.save(alert);
	}
	

	public Alert findOne(int id) {
		return alertRepository.findOne(id);
	}
	
	public List<Alert> findAll() {
		return alertRepository.findAll();
	}
	
	public List<MailMail> findMatchingAds(){
		List<Alert> alerts = alertRepository.findAll();
		System.out.println("Alerts size: "+alerts.size());
		List<Ad> ads = adService.findAll();
		List<MailMail> mails = new ArrayList<MailMail>();
		for(Alert alert : alerts){
			for(Ad ad : ads){
				
				List<Ad> matchingAds = new ArrayList<Ad>();
					
				if(alertMatchesAd(alert, ad)){					
					matchingAds.add(ad);
				
				}
				
				System.out.println("number of matching Ads: "+ matchingAds.size());
				
				if(!matchingAds.isEmpty()){
					for(Ad matchingAd : matchingAds){
						long actualTime = new Date().getTime();
					    long ageOfAd = matchingAd.getPublishedDate().getTime();
					    //check if ad is older than a day
						if((actualTime-ageOfAd)<(24 * 60 * 60 * 1000)){
					    	MailMail mail = new MailMail();
				    		mail.setFrom("ese2014team3@gmail.com");
				    		mail.setRecipients(alert.getUser().getEmail());
				    		mail.setSubject("New interesting room for you");
				    		mail.setText("Hi,\n"
				    				+ "We have found a new interesting room for you. \n"
				    				+ LOCALHOSTNAME+"/room4you/ads/"+ad.getId()+".html \n"			    				
				    				+ "Checkout it out!");
				    		mails.add(mail);
													
						}
					}
					
				}
			}
	}
	
	return mails;
	
	}

	



	private boolean alertMatchesAd(Alert alert, Ad ad) {
		return 	(alert.getCity().toLowerCase().equals(ad.getCity().toLowerCase()) &&
				alert.getZip().equals(ad.getZip()) &&
				alert.getNbrRoomsMatesMin()<=ad.getNbrRoomsMates() &&
				alert.getNbrRoomsMatesMax()>=ad.getNbrRoomsMates() &&
				alert.getNbrRoomsMin()<=ad.getNbrRooms() &&
				alert.getNbrRoomsMax()>=ad.getNbrRooms() &&
				alert.getRentPerMonthMin()<=ad.getRentPerMonth() &&
				alert.getRentPerMonthMax()>=ad.getRentPerMonth());
	}
}
