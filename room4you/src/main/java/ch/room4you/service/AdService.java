package ch.room4you.service;

/**
 * Database operation service for roomMateRepository interface
 */

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Appointment;
import ch.room4you.entity.AppointmentDate;
import ch.room4you.entity.Image;
import ch.room4you.entity.RoomMate;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.AppointmentDateRepository;
import ch.room4you.repository.AppointmentRepository;
import ch.room4you.repository.ImageRepository;
import ch.room4you.repository.MessageRepository;
import ch.room4you.repository.RoomMateRepository;
import ch.room4you.repository.UserRepository;

@Service
public class AdService {
	@Autowired
	private AdRepository adRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private AppointmentDateRepository dateRepository;

	@Autowired
	private RoomMateRepository roomMateRepository;
	
	@Autowired
	private MessageService messageService;
	
	  static Logger log = Logger.getLogger(
              AdService.class.getName());

	/**
	 * Saves the ad in the database
	 * 
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
		messageService.deleteAllMessages(ad.getAdMessages());
		Ad tempAd = adRepository.findOne(ad.getId());
		adRepository.delete(tempAd);
	}

	@Transactional
	public Ad findOne(int id) {
		return adRepository.findOne(id);
	}

	@Transactional
	public List<Ad> findAll() {
		return adRepository.findAll();
	}

	@Transactional
	public List<Ad> findAdsWithFormCriteria(String city, String zip,
			int priceMin, int priceMax, int searchTextNbrRoomMatesMin,
			int searchTextNbrRoomMatesMax, float searchTextNbrRoomsMin,
			float searchTextNbrRoomsMax, boolean sharedApartment) {
		List<Ad> ads = adRepository.findAdsWithFormCriteria("%" + city + "%",
				"%" + zip + "%", priceMin, priceMax, searchTextNbrRoomMatesMin,
				searchTextNbrRoomMatesMax, searchTextNbrRoomsMin,
				searchTextNbrRoomsMax, sharedApartment);
		for (Ad ad : ads) {
			List<Image> images = imageRepository.findByAd(ad);
			ad.setImages(images);
		}
		return ads;

	}

	@Transactional
	public void doAddAd(Model model, Ad ad, BindingResult result,
			Principal principal, MultipartFile[] images, List<String> roomMate, WebRequest webRequest,
			List<String> appointments) {

		if (ad.getWeAreLookingFor().isEmpty()) {
			ad.setWeAreLookingFor("Anyone");
		}

		String name = principal.getName();
		save(ad, name);

		try {

			// save roommates
			if (!roomMate.get(0).equals("anonymous")) {
				saveRoomMates(ad, roomMate);
			}

			if (!appointments.isEmpty()) {
				List<Appointment> adAppointments = createAppointments(ad, appointments);
				ad.setAppointments(adAppointments);
				adRepository.save(ad);
			}

			// save imagesAsString
			if (!images[0].isEmpty())
				saveImages(ad, images);

		} catch (IOException e) {
			log.error("Could not load image", e);
			e.printStackTrace();
		}

	}

	/**
	 * Appointments are being created according to the users input. An appointment has an appointmentDate
	 * which is created first, the appointment gets assigned numbrVisitors and the corresponding ad.
	 * 
	 * This the first element of the List appointments must not be null, otherwise the method does not execute
	 * anything. 
	 * 
	 * @param ad
	 * @param appointments, List of Strings, format: <Date, startTime, endtTime, nmbrVisitors>
	 * @return List of created Appointments 
	 */
	private List<Appointment> createAppointments(Ad ad, List<String> appointments) {
		List<Appointment> createdAppointments = new ArrayList<Appointment>();
	
		for (int i = 0; i < appointments.size(); i += 4) {
			if (!appointments.get(i).isEmpty()) {
				AppointmentDate appointDate = createAppointmentDate(appointments, i);
				Appointment appointment = new Appointment();
				appointment.setAppointDate(appointDate);
				appointment.setAppointmentAd(ad);

				if (!appointments.get(i + 3).isEmpty()) {
					appointment.setNmbrVisitors(Integer.valueOf(appointments.get(i + 3)));
				}
				// Default value, in case the user does not set any value. An appointment with 0 visitors would not make sense
				else appointment.setNmbrVisitors(20); 
					
				appointmentRepository.save(appointment);
				createdAppointments.add(appointment);
			}
		}
		return createdAppointments;
	}

	/**
	 * Creates an appointDate for an Appointment when an Ad has been created.
	 * 
	 * @param appointments
	 * @param i, index of List appointments in order to create the appointDate(s) correctly. 
	 * @return
	 */
	private AppointmentDate createAppointmentDate(List<String> appointments, int i) {
		AppointmentDate appointDate = new AppointmentDate();
		appointDate.setAppointDate(appointments.get(i));
		appointDate.setStartTime(appointments.get(i + 1));
		appointDate.setEndTime(appointments.get(i + 2));
		dateRepository.save(appointDate);
		return appointDate;
	}

	private void saveRoomMates(Ad ad, List<String> roomMates) {
	//	List<String> roomMates = Arrays.asList(roomMate.split(","));
		for (String roomM : roomMates) {
			RoomMate rm = new RoomMate();
			rm.setUser(userRepository.findOne(Integer.parseInt(roomM)));
			rm.setAd(ad);
			roomMateRepository.save(rm);
		}
	}

	@Transactional
	private void saveImages(Ad ad, MultipartFile[] images) throws IOException {
		byte[] bytes;
		for (MultipartFile imageMPF : images) {
			Image image = new Image();
			if (!imageMPF.isEmpty()) {
				bytes =  imageMPF.getBytes();
				byte[] encoded = Base64.encodeBase64(bytes);
				String encodedString = new String(encoded);
				image.setImageAsString(encodedString);
				image.setAd(ad);
				imageRepository.save(image);
			}

		}
	}
	
	/**
	 * Adds further appointments, when the ad has been edited. 
	 * 
	 * @param editedAd
	 * @param appointments, List of Strings with the format <Date, startTime, endTime, nmbrVisitors>
	 */
	private void addAppointments(Ad ad, List<String> appointments) {
		List<Appointment> currentAppointments = ad.getAppointments();
		List<Appointment> additionalAppointments = createAppointments(ad, appointments);
		
		currentAppointments.addAll(additionalAppointments);
		
			if (!currentAppointments.isEmpty()) {
				ad.setAppointments(currentAppointments);
				adRepository.save(ad);
		}
	}


	@Transactional
	public void editAd(int id, Model model, Ad ad, BindingResult result,
			Principal principal, MultipartFile[] images, List<String> roomMate,WebRequest webRequest, List<String> appointments) {
		

		String name = principal.getName();
		ad.setId(id);
		save(ad, name);

		try {

			// save roommates
			if (!roomMate.get(0).equals("anonymous")) {
				saveRoomMates(ad, roomMate);
			}
			
			if (!appointments.isEmpty()) {
				List<Appointment> adAppointments = createAppointments(ad, appointments);
				ad.setAppointments(adAppointments);
				adRepository.save(ad);
			}

			// save imagesAsString
			if (!images[0].isEmpty()) {
				saveImages(ad, images);
			}

		} catch (IOException e) {
			log.error("Could not save Image", e);
			e.printStackTrace();
		} 
	}

}
