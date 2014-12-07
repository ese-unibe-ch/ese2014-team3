package ch.room4you.service;

/**
 * Database operation service for imageRepository interface
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ch.room4you.entity.Ad;
import ch.room4you.entity.Image;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.ImageRepository;
import ch.room4you.repository.UserRepository;

@Service
public class ImageService{
	@Autowired
	private AdRepository adRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	

	

	/**
	 * Saves the image in the database
	 * @param image
	 */
	public void save(Image image, int id) {
		Ad ad = adRepository.findOne(id);
		image.setAd(ad);
		imageRepository.save(image);
		ad.addImage(image);
		adRepository.save(ad);
	}
	


	public void save(Image image) {
		imageRepository.save(image);
		
	}
	
	public void delete(Image image) {
		imageRepository.delete(image);
		
	}
	
	public Image findOneById(int id) {
		return imageRepository.findOne(id);
		
	}

}
