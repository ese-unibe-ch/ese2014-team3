package ch.room4you.service;

/**
 * Database operation service for roomMateRepository interface
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
		List<Ad> ads = adService.findAll();
		List<MailMail> mails = new ArrayList<MailMail>();
		for(Alert alert : alerts){
			for(Ad ad : ads){
				
				List<Ad> matchingAds = new ArrayList<Ad>();
					
				if(alertMatchesAd(alert, ad)){					
					matchingAds.add(ad);				
				}
						
				if(!matchingAds.isEmpty()){
					for(Ad matchingAd : matchingAds){
						long actualTime = new Date().getTime();
					    long ageOfAd = matchingAd.getPublishedDate().getTime();
					    //check if ad is older than a day
						if((actualTime-ageOfAd)<(24 * 60 * 60 * 1000)){
					    	MailMail mail = new MailMail();
				    		mail.setFrom(getHostNameFromProperties().getProperty("emailAccount"));
				    		mail.setRecipients(alert.getUser().getEmail());
				    		mail.setSubject("New interesting room for you");
				    		mail.setText("Hi,\n"
				    				+ "We have found a new interesting room for you. \n"
				    				+ getHostNameFromProperties().getProperty("hostName")+"/ads/"+ad.getId()+".html \n"			    				
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
		String alertCity = "";
		String alertZip = "";
		int alertRoomMatesMin = 0;
		int alertRoomMatesMax = Integer.MAX_VALUE;
		float alertRoomsMin = 0;
		float alertRoomsMax = Integer.MAX_VALUE;
		int alertRentMin = 0;
		int alertRentMax = Integer.MAX_VALUE;
		
		if(!alert.getCity().isEmpty() || alert.getCity()!=null)
		alertCity = alert.getCity();
		
		if(!alert.getZip().isEmpty() || alert.getZip()!=null)
		alertZip = alert.getZip();
		
		if(alert.getNbrRoomsMatesMin() > 0)
		alertRoomMatesMin = alert.getNbrRoomsMatesMin();
		
		if(alert.getNbrRoomsMatesMax()<Integer.MAX_VALUE);
		alertRoomMatesMax = alert.getNbrRoomsMatesMax();
		
		if(alert.getNbrRoomsMin() > 0)
		alertRoomsMin = alert.getNbrRoomsMin();
		
		if(alert.getNbrRoomsMax() < Integer.MAX_VALUE)
		alertRoomsMax = alert.getNbrRoomsMax();
		
		if(alert.getRentPerMonthMin() > 0)
		alertRentMin = alert.getRentPerMonthMin();
		
		if(alert.getRentPerMonthMax()< Integer.MAX_VALUE)
		alertRentMax = alert.getRentPerMonthMax();
		
		return 	(alertCity.toLowerCase().equals(ad.getCity().toLowerCase()) &&
				alertZip.equals(ad.getZip()) &&
				alertRoomMatesMin<=ad.getNbrRoomsMates() &&
				alertRoomMatesMax>=ad.getNbrRoomsMates() &&
				alertRoomsMin<=ad.getNbrRooms() &&
				alertRoomsMax>=ad.getNbrRooms() &&
				alertRentMin<=ad.getRentPerMonth() &&
				alertRentMax>=ad.getRentPerMonth());
	}
	
	private Properties getHostNameFromProperties(){
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream("config.properties");
	 
			// load a properties file
			prop.load(input);

	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;
		
	}
}
