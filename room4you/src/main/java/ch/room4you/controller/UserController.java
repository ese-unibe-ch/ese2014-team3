package ch.room4you.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Appointment;
import ch.room4you.entity.AppointmentDate;
import ch.room4you.entity.FavCandidates;
import ch.room4you.entity.Image;
import ch.room4you.entity.Message;
import ch.room4you.entity.RoomMate;
import ch.room4you.entity.User;
import ch.room4you.repository.FavCandidatesRepository;
import ch.room4you.repository.MessageRepository;
import ch.room4you.service.AdService;
import ch.room4you.service.AppointmentDateService;
import ch.room4you.service.AppointmentService;
import ch.room4you.service.FavCandidatesService;
import ch.room4you.service.ImageService;
import ch.room4you.service.MessageService;
import ch.room4you.service.RoomMateService;
import ch.room4you.service.UserService;
import ch.room4you.service.BookmarkService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdService adService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private RoomMateService roomMateService;

	@Autowired
	private AppointmentDateService dateService;

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@Autowired
	private FavCandidatesService candidateService;
	
	


	
	@ModelAttribute("favCandidates")
	public FavCandidates constructFavCandidates(){
		return new FavCandidates();
	}

	/**
	 * Maps the request url /account to the page account.jsp and provides the
	 * model "user" with the current user
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithAds(name));
		model.addAttribute("users", userService.findAll());
		model.addAttribute("userm", messageService.findOneWithMessages(name));
		//model.addAttribute("conversations", messageService.findFirstMessageOfConversations(userService.findOneByName(name), userService.findOneByName(name)));		
		model.addAttribute("bookmarks", bookmarkService.findAllBookmarks(name));
		model.addAttribute("candidates", candidateService.findByAdPlacer(name));

		return "account";
	}

	/**
	 * Receives the data from user-account form and adds the data to the ad
	 * object and saves the ad to the current user in the database. Provides
	 * model ad.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	@Transactional
	public String doAddAd(Model model, @ModelAttribute("ad") Ad ad,
			BindingResult result,
			Principal principal,
			@RequestParam("image[]") MultipartFile[] images
			// ,@RequestParam("roomMates") String roomMate
			, org.springframework.web.context.request.WebRequest webRequest,
			@RequestParam("appointments") List<String> appointments) {

	//	adService.doAddAd(model, ad, result, principal, images, webRequest, appointments);
		/*String roomMate = webRequest.getParameter("roomMates");

		if (ad.getWeAreLookingFor().isEmpty()) {
			ad.setWeAreLookingFor("Anyone");
		}

		String name = principal.getName();
		adService.save(ad, name);

		try {

			// save roommates
			if (roomMate != null) {
				saveRoomMates(ad, roomMate);
			}

			if (appointments != null) {
				saveAppointments(ad, appointments);
			}

			// save imagesAsString
			if (!images[0].isEmpty())
				saveImages(ad, images);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	/*	List<FavCandidates> f = favCRepository.findAll();
		System.err.println(f.isEmpty());
		System.out.println(f);
		for ( FavCandidates c : f) {
			System.out.println(c.getVisitors().get(0));
		} */ 
		return "redirect:/account.html";
	}

	

	/**
	 * Removes the user with id = {id} and logs the user out
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/account/remove/{id}")
	public String removeUser(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/logout";
	}

	/*
	 * @RequestMapping("/ad/removeBookmarkAd/{id}") public String
	 * unBookmarkAd(@PathVariable int id, Principal principal) { String userName
	 * = principal.getName(); User user = userService.findOneByName(userName);
	 * userService.unBookmarkAd(user, id); return "redirect:/account.html"; }
	 */

/*	private void saveAppointments(Ad ad, List<String> appointments) {
		List<Appointment> adAppoints = new ArrayList<Appointment>();
		for (int i = 0; i < appointments.size(); i += 4) {
			AppointmentDate appointDate = new AppointmentDate();
			appointDate.setAppointDate(appointments.get(i));
			appointDate.setStartTime(appointments.get(i + 1));
			appointDate.setEndTime(appointments.get(i + 2));
			dateService.save(appointDate);
			Appointment appointment = new Appointment();
			appointment.setAppointDate(appointDate);
			appointment.setAppointmentAd(ad);

			if (!appointments.get(i + 3).isEmpty()) {
				appointment.setNmbrVisitors(Integer.valueOf(appointments
						.get(i + 3)));
			}
			appointmentService.save(appointment);
			adAppoints.add(appointment);
		}
		ad.setAppointments(adAppoints);
	}

	private void saveRoomMates(Ad ad, String roomMate) {
		List<String> roomMates = Arrays.asList(roomMate.split(","));
		for (String roomM : roomMates) {
			RoomMate rm = new RoomMate();
			rm.setUser(userService.findOne(Integer.parseInt(roomM)));
			rm.setAd(ad);
			roomMateService.save(rm);
		}
	}

	private void saveImages(Ad ad, MultipartFile[] images) throws IOException {
		byte[] bytes;
		for (MultipartFile imageMPF : images) {
			Image image = new Image();
			if (!imageMPF.isEmpty()) {
				bytes = imageMPF.getBytes();
				byte[] encoded = Base64.encodeBase64(bytes);
				String encodedString = new String(encoded);
				image.setImageAsString(encodedString);
				image.setAd(ad);
				imageService.save(image);
			}

		}
	} */ 

	/**
	 * Maps the date format to the convenient date format for the database
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}
