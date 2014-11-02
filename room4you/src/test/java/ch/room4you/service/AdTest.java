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
public class AdTest {
 
 
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
		
	}
    
	    
	    /**
	     * Checks if Ad can be saved
	     */
	    @Test
	    public void saveAd() {
	        // given
	    	Date date = new Date();
		    Timestamp timestamp = new java.sql.Timestamp(date.getTime()-1000);
	        final Ad ad = createNewAd(timestamp);
	        // when
	        adRepository.save(ad);
	        // then
	        Mockito.verify(adRepository, Mockito.times(1)).save(ad);
	    }
	    

	    /**
	     * Checks if Ad can be found by User
	     */
	    @Test
	    public void findAdByUser() {
	        // given
	        List<Ad> adList = new ArrayList<Ad>();
	        adList.add(ad);
	        Mockito.doReturn(adList).when(adRepository).findByUser(user);
	 	        // then
	        assertTrue(adList.get(0).getUser()==user);
	    }	


	    /**
	     * Checks if new ad is created correctly
	     */
		@Test
		public void testNewAdCreation() {
			assertTrue(ad.getPublishedDate()==timestamp);
			assertEquals("Bern", ad.getCity());
			
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


		private User createUser() {
			user.setEmail("test@b.ch");
			user.setName("TestUser");
			return user;
		}
		
}
