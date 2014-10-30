package ch.room4you.service;

/**
 * Database operation service for roomMateRepository interface
 */

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Image;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.ImageRepository;
import ch.room4you.repository.UserRepository;

@Service
public class AdService{
	@Autowired
	private AdRepository adRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	

	/**
	 * Saves the ad in the database
	 * @param ad
	 * @param name
	 */
	public void save(Ad ad, String name) {
		User user = userRepository.findByName(name);
		ad.setUser(user);
		adRepository.save(ad);
	}
	


	@PreAuthorize("#ad.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("ad") Ad ad) {
		adRepository.delete(ad);
	}

	public Ad findOne(int id) {
		return adRepository.findOne(id);
	}
	
	public List<Ad> findAll() {
		return adRepository.findAll();
	}

	

	
	@Transactional
	public List<Ad> findAdsWithFormCriteria(String city, String zip, int priceMin, int priceMax, int searchTextNbrRoomMatesMin, int searchTextNbrRoomMatesMax, float searchTextNbrRoomsMin, float searchTextNbrRoomsMax, boolean sharedApartment) {	
		List<Ad> ads = adRepository.findAdsWithFormCriteria("%"+city+"%", "%"+zip+"%", priceMin, priceMax, searchTextNbrRoomMatesMin, searchTextNbrRoomMatesMax, searchTextNbrRoomsMin, searchTextNbrRoomsMax, sharedApartment);	
		for (Ad ad : ads) {
			List<Image> images = imageRepository.findByAd(ad);
			ad.setImages(images);
		}
	return ads;	

	}

}
