package ch.room4you.service;

import java.util.List;

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
public class AdService {
	@Autowired
	private AdRepository adRepository;
	
	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	// 1 hour = 60 seconds * 60 minutes * 1000
	@Scheduled(fixedDelay=3600000)
	public void reloadAds() {
		List<Ad> ads = adRepository.findAll();
	}
	

	public void save(Ad ad, String name) {
		User user = userRepository.findByName(name);
		ad.setUser(user);
		adRepository.save(ad);
		saveImages(ad);
	}
	

	
	public void saveImages(Ad ad) {
		List<Image> images = imageRepository.findByAd(ad);
		for(Image image : images){
			image.setAd(ad);
			imageRepository.save(image);
		}
	
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
	

//	public List<Ad> getItems() {
//		return adRepository.findAll(new PageRequest(0, 20, Direction.DESC, "publishedDate")).getContent();
//	}

}
