package ch.room4you.service;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Alert;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.AlertRepository;
//import pojo.MailMail;

public class AlertTest {
	@Autowired
	private AlertService alertService;
	@Autowired
	private AlertRepository alertRepository;
	
	@Autowired
	private AdRepository adRepository;
	
	@Autowired
	private AdService adService;
	

	@Test
	public void test() {
		
		Ad ad = createNewAd();		
		long actualTime = new Date().getTime();
	    long ageOfAd = ad.getPublishedDate().getTime();		
		Alert alert = createNewAlert();
				
		System.out.println("Alert: "+alert);
		
		assertTrue(ad!=null);
		assertTrue(actualTime-ageOfAd>1000);
		

		
//		List<Ad> matchingAds = adService.findAdsWithFormCriteria(alert.getCity(), 
//				alert.getZip(), 
//				alert.getRentPerMonthMin(), 
//				alert.getRentPerMonthMax(), 
//				alert.getNbrRoomsMatesMin(), 
//				alert.getNbrRoomsMatesMax(), 
//				alert.getNbrRoomsMin(),
//				alert.getNbrRoomsMax(), true || false);
		
		List<Ad> matchingAds = adService.findAdsWithFormCriteria("Bern", "3007", 100, 1900, 1, 5, 1, 5 , false);
		
		
		assertTrue(matchingAds.size()==1);
		

	}


	private Alert createNewAlert() {
		Alert alert = new Alert();
		alert.setCity("Bern");
		alert.setZip("3007");
		alert.setNbrRoomsMatesMin(1);
		alert.setNbrRoomsMatesMax(4);
		alert.setNbrRoomsMin(2);
		alert.setNbrRoomsMax(5);
		alert.setRentPerMonthMin(300);
		alert.setRentPerMonthMax(1900);
		return alert;
	}


	
	private Ad createNewAd() {
		Ad ad = new Ad();
		Timestamp timestamp = Timestamp.valueOf("2014-10-31 15:00:00.0");
		ad.setPublishedDate(timestamp);
		ad.setCity("Bern");
		ad.setZip("3007");
		ad.setNbrRooms(4);
		ad.setRentPerMonth(1200);
		ad.setNbrRoomsMates(3);
		testNewAdCreation(ad, timestamp);
		return ad;
		
	}


	
	private void testNewAdCreation(Ad ad, Timestamp timestamp) {
		assertTrue(ad.getPublishedDate()==timestamp);
		assertEquals("Bern", ad.getCity());
		
	}

}
