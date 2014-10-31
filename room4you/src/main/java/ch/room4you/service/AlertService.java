package ch.room4you.service;

/**
 * Database operation service for roomMateRepository interface
 */

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
