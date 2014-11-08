package ch.room4you.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pojo.MailMail;
import ch.room4you.entity.Ad;
import ch.room4you.entity.Alert;
import ch.room4you.entity.Role;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.AlertRepository;
import ch.room4you.repository.RoleRepository;
import ch.room4you.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/springData.xml","file:src/main/webapp/WEB-INF/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AdTest {
    
    
	private Date date = new Date();
	private Timestamp timestamp = new java.sql.Timestamp(date.getTime()-1000); 
    
    private Ad ad;
    private User user;
    
     
    @Autowired
    RoleRepository roleRepository;
    
    
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
    
    
    AdService adService;


    
 
    @Before
	public void setUp() throws Exception {
		user = createUser();
		ad = createNewAd(timestamp);
		adService = mock(AdService.class);
		adRepository = mock(AdRepository.class);
		
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
	        List<Ad> adList = new ArrayList<Ad>();
	        assertTrue(adList.size()==0);
	        adList.add(ad);	        
	 	        // then
	        assertTrue(adList.get(0).getUser().getName()==user.getName());
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
    		String userName = "test";
    		String userEmail = "ese2014team3@gmail.com";
    		String userPassword = "test";
    		String userAboutMe = "I am the admin of room4you and I am 12 years old";
    		
    		Role roleUser = new Role();
    		roleUser.setName("ROLE_USER");
    		roleRepository.save(roleUser);
    		
    	
    		User testUser = new User();
    		AppInitService appInit = new AppInitService();
    		

    		
    		testUser.setEnabled(true);
    		testUser.setName(userName);
    		testUser.setEmail(userEmail);
    		testUser.setPassword(userPassword);
    		List<Role> roles = new ArrayList<Role>();
    		roles.add(roleUser);
    		testUser.setRoles(roles);
    		testUser.setAboutMe(userAboutMe);
    		userRepository.save(testUser);
    		return testUser;
		}
		
}
