package ch.room4you.service;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import pojo.MailMail;
import ch.room4you.entity.Ad;
import ch.room4you.entity.Alert;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.AlertRepository;
import ch.room4you.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class AlertTest {
 
 
    @Autowired
	@Mock
    private AdRepository adRepository;
    
    @Autowired
	@Mock
    private AlertRepository alertRepository;
    
    @Autowired
	@Mock
    private UserRepository userRepository;
    
    @Autowired
	@Mock
    private AlertService alertService;
    
    @Autowired
	@Mock
    private AdService adService;
    
    @Autowired
 	@Mock
    private Alert alert;
    
    @Autowired
 	@Mock
    private Ad ad;
    
    
    @Autowired
 	@Mock
    private User user;
    
    @Autowired
 	@Mock
    private MailMail mail;
    
    
	private Date date = new Date();
	private Timestamp timestamp = new java.sql.Timestamp(date.getTime()-1000); 
    
    
    
 
    @Before
	public void setUp() throws Exception {
		ad = createNewAd(timestamp);
		user = createUser();
		adRepository.save(ad);
		alert = createNewAlert();	
		mail = createNewMail();
		
	}

	    @Test
		public void test() {
			long actualTime = new Date().getTime();
		    long ageOfAd = ad.getPublishedDate().getTime();
			assertTrue(ad!=null);
			assertTrue((actualTime-ageOfAd)<(24 * 60 * 60 * 1000));	
							
		}

    
	    
		@Test
		public void findAdsMatchingToAlertCriteria() {
	        List<MailMail> mailList = new ArrayList<MailMail>();
	        mailList.add(mail);	   	
	        
	        List<Ad> adList = new ArrayList<Ad>();
	        adList.add(ad);
	        Mockito.doReturn(adList).when(adRepository).findAll();	 
	        
	        List<Alert> alertList = new ArrayList<Alert>();
	        alertList.add(alert);	
	        
	        Mockito.doReturn(alertList).when(alertRepository).findAll();	        
	        Mockito.doReturn((mailList)).when(alertService).findMatchingAds();	
	        	        
			assertEquals(mailList, alertService.findMatchingAds());
			
		}


		
		@Test
		public void createNewAlertTest() {
			createNewAlert();
		}
		
		
	   @After
	    public void tearDown() {
	        System.out.println("@After - tearDown");
	    }

				

		private Ad createNewAd(Timestamp timestamp) {
			Ad ad = new Ad();
			ad.setPublishedDate(timestamp);
			ad.setCity("Bern");
			ad.setZip("3007");
			ad.setNbrRooms(4);
			ad.setRentPerMonth(1200);
			ad.setNbrRoomsMates(3);			
			ad.setUser(user);
			return ad;
		}
		
		private MailMail createNewMail() {
			MailMail mail = new MailMail();
			mail.setFrom("Test");
			return mail;
		}

		private User createUser() {
			user.setEmail("test@b.ch");
			user.setName("TestUser");
			return user;
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
		
}
