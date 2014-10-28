package ch.room4you.service;

/**
 * Database operation service for roomMateRepository interface
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Ad;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.UserRepository;

@Service
public class AdService{
	@Autowired
	private AdRepository adRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	

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

}
