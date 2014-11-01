package ch.room4you.service;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Alert;
import ch.room4you.repository.AdRepository;

@RunWith(MockitoJUnitRunner.class)
public class AlertTest {
 
 
    @Autowired
	@Mock
    private AdRepository adRepository;
    

 


	    @Test
		public void test() {
	    	Date date = new Date();
			Timestamp timestamp = new java.sql.Timestamp(date.getTime()-1000); 
			Ad ad = createNewAd(timestamp);		
			long actualTime = new Date().getTime();
		    long ageOfAd = ad.getPublishedDate().getTime();		
			Alert alert = createNewAlert();			
			assertTrue(ad!=null);
			assertTrue((actualTime-ageOfAd)<(24 * 60 * 60 * 1000));
			

			
			List<Ad> matchingAds = adRepository.findAdsWithFormCriteria(alert.getCity(), 
					alert.getZip(), 
					alert.getRentPerMonthMin(), 
					alert.getRentPerMonthMax(), 
					alert.getNbrRoomsMatesMin(), 
					alert.getNbrRoomsMatesMax(), 
					alert.getNbrRoomsMin(),
					alert.getNbrRoomsMax(), true || false);			
			
			assertTrue(matchingAds.size()==0);
			
	
			
		}


		@Test
		public void testNewAdCreation() {
			Timestamp timestamp = Timestamp.valueOf("2014-10-31 15:00:00.0");
			Ad ad = createNewAd(timestamp);
			assertTrue(ad.getPublishedDate()==timestamp);
			assertEquals("Bern", ad.getCity());
			
		}
		
		@Test
		public void createNewAlertTest() {
			Alert alert = createNewAlert();
		}

				

		private Ad createNewAd(Timestamp timestamp) {
			Ad ad = new Ad();
			ad.setPublishedDate(timestamp);
			ad.setCity("Bern");
			ad.setZip("3007");
			ad.setNbrRooms(4);
			ad.setRentPerMonth(1200);
			ad.setNbrRoomsMates(3);
			return ad;
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
