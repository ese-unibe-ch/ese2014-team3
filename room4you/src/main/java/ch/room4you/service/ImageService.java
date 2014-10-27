package ch.room4you.service;

/**
 * Database operation service for adRepository interface
 */
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Image;
import ch.room4you.entity.User;
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
	

	
//	public void saveImages(Ad ad) {
//		List<Image> images = imageRepository.findByAd(ad);		
//		for(Image image : images){
//			image.setAd(ad);
//			imageRepository.save(image);
//		}
//	
//	}
	
//	public void saveImage(Ad ad){
//		
//		
//		
//	}

	

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



	public void save(Image image) {
		imageRepository.save(image);
		
	}
	
//	 @Transactional
//	    public AdForm saveForm(AdForm adForm){
//
//		 	Long id = adForm.getId();
//	        String title = adForm.getTitle();
//	        String description = adForm.getDescription();
//	        String street = adForm.getStreet();
//	        int zip = adForm.getZip();
//	        String city = adForm.getCity();
//	        String additionalInformation = adForm.getAdditionalInformation();
//	        String rent = adForm.getRentPerMonth();
//	        float nbrOfRooms = adForm.getNbrRooms();
//	        Date creationDate = adForm.getPublishedDate();
//
//	        Ad ad = new Ad();
//	        ad.setId(id);
//			ad.setTitle(title);
//			ad.setPublishedDate(creationDate);
//			ad.setDescription(description);
//	        ad.setUser(adForm.getUser());
//	        ad.setStreet(street);
//	        ad.setCity(city);
//	        ad.setZip(zip);
//	        ad.setAvailableFrom(adForm.getAvailableFrom());
//	        ad.setNbrRooms(nbrOfRooms);
//	        ad.setAdditionalInformation(additionalInformation);
//
//	        return adForm;
//
//	    }

	

//	public List<Ad> getItems() {
//		return adRepository.findAll(new PageRequest(0, 20, Direction.DESC, "publishedDate")).getContent();
//	}

}
