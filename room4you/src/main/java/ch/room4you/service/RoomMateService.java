package ch.room4you.service;

/**
 * Database operation service for adRepository interface
 */

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Ad;
import ch.room4you.entity.RoomMate;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.RoomMateRepository;
import ch.room4you.repository.UserRepository;

@Service
public class RoomMateService{
	@Autowired
	private AdRepository adRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoomMateRepository roomMateRepository;
	
	

	/**
	 * Saves the ad in the database
	 * @param ad
	 * @param name
	 */
	public void save(RoomMate roomMate, int id) {
		Ad ad = adRepository.findOne(id);
		roomMate.setAd(ad);
		roomMateRepository.save(roomMate);
		ad.addRoomMate(roomMate);
		adRepository.save(ad);
	}
	
	public void save(RoomMate roomMate) {
		roomMateRepository.save(roomMate);
		
	}
	
	public void deleteRoomMate(RoomMate roomMate) {
		roomMateRepository.delete(roomMate);
		
	}
	//added transactional
	@Transactional
	public List<RoomMate> findRoomMatesForAd(Ad ad){
		return roomMateRepository.findByAd(ad);		
	}
	


}
