package ch.room4you.service;

/**
 * Database operation service for roomMateRepository interface
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.MailMail;
import ch.room4you.entity.Ad;
import ch.room4you.entity.Alert;
import ch.room4you.entity.Image;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.AlertRepository;
import ch.room4you.repository.ImageRepository;
import ch.room4you.repository.UserRepository;

@Service
public class AlertService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private AlertRepository alertRepository;
	
	@Autowired
	private AdRepository adRepository;
	
	
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
		List<MailMail> mails = new ArrayList<MailMail>();
		for(Alert alert : alerts){
			List<Ad> matchingAds = findAdsWithFormCriteria(alert.getCity(), 
					alert.getZip(), 
					alert.getRentPerMonthMin(), 
					alert.getRentPerMonthMax(), alert.getNbrRoomsMatesMin(), alert.getNbrRoomsMatesMax(), 
					alert.getNbrRoomsMin(),
					alert.getNbrRoomsMax());
			
			System.out.println("number of matching Ads: "+ matchingAds.size());
			
			if(!matchingAds.isEmpty()){
				for(Ad matchingAd : matchingAds){
					long actualTime = new Date().getTime();
				    long ageOfAd = matchingAd.getPublishedDate().getTime();
				    //check if ad is older than a day
//					if((actualTime-ageOfAd)>(24 * 60 * 60 * 1000)){
				    if((actualTime-ageOfAd)>(1000)){
				    	MailMail mail = new MailMail();
			    		mail.setFrom("ese2014team3@gmail.com");
			    		mail.setRecipients(alert.getUser().getEmail());
			    		mail.setSubject("New interesting room for you");
			    		mail.setText("Hi,\n"
			    				+ "We have found a new interesting room for you. \n"
			    				+ "www.google.ch \n"			    				
			    				+ "Checkout your profile");
			    		mails.add(mail);
												
					}
				}
				
			}
		}
		
		return mails;
		
	}
	
	@Transactional
	public List<Ad> findAdsWithFormCriteria(String city, String zip, int priceMin, int priceMax, int searchTextNbrRoomMatesMin, int searchTextNbrRoomMatesMax, float searchTextNbrRoomsMin, float searchTextNbrRoomsMax) {	
		List<Ad> ads = adRepository.findAdsWithFormCriteria("%"+city+"%", "%"+zip+"%", priceMin, priceMax, searchTextNbrRoomMatesMin, searchTextNbrRoomMatesMax, searchTextNbrRoomsMin, searchTextNbrRoomsMax);	
		for (Ad ad : ads) {
			List<Image> images = imageRepository.findByAd(ad);
			ad.setImages(images);
		}
	return ads;	

	}
	
}
