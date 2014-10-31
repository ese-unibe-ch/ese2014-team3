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
import pojo.MailMail;

public class AlertTest {
	@Autowired
	private AlertService alertService;
	@Autowired
	private AlertRepository alertRepository;
	
	@Autowired
	private AdRepository adRepository;
	

	@Test
	public void test() {
//		Ad ad = new Ad();
//		Timestamp timestamp = Timestamp.valueOf("2014-10-31 15:00:00.0");
//		long actualTime = new Date().getTime();
//	    long ageOfAd = timestamp.getTime();
//		ad.setPublishedDate(timestamp);
//		ad.setCity("Bern");
//		ad.setZip("3007");
//		ad.setNbrRooms((float) 4.5);
//		ad.setRentPerMonth(1200);
//		ad.setNbrRoomsMates(3);
//		
//		assertTrue(ad.getPublishedDate()==timestamp);
//		assertEquals("Bern", ad.getCity());
//		
//		Alert alert = new Alert();
//		alert.setCity("Bern");
//		alert.setZip("3007");
//		alert.setNbrRoomsMatesMax(4);
//		alert.setNbrRoomsMatesMin(1);
//		alert.setNbrRoomsMin(2);
//		alert.setNbrRoomsMax(5);
//		alert.setRentPerMonthMin(300);
//		alert.setRentPerMonthMax(1300);
//		
//		
//		
//		assertTrue(ad!=null);
//		assertTrue(actualTime-ageOfAd>1000);
//		
//
//		
//		List<Ad> matchingAds = alertRepository.findAdsWithFormCriteria(alert.getCity(), 
//				alert.getZip(), 
//				alert.getRentPerMonthMin(), 
//				alert.getRentPerMonthMax(), 
//				alert.getNbrRoomsMatesMin(), 
//				alert.getNbrRoomsMatesMax(), 
//				alert.getNbrRoomsMin(),
//				alert.getNbrRoomsMax());
//		
//		assertTrue(matchingAds.size()==1);
		

	}

}
