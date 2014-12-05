package ch.room4you.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
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

import pojo.SearchAdsForm;
import ch.room4you.entity.Ad;
import ch.room4you.entity.Appointment;
import ch.room4you.entity.Image;
import ch.room4you.entity.RoomMate;
import ch.room4you.entity.User;
import ch.room4you.service.AdService;
import ch.room4you.service.AppointmentService;
import ch.room4you.service.BookmarkService;
import ch.room4you.service.FavCandidatesService;
import ch.room4you.service.ImageService;
import ch.room4you.service.MessageService;
import ch.room4you.service.RoomMateService;
import ch.room4you.service.UserService;

@Controller
public class AdController {

	@Autowired
	private AdService adService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private FavCandidatesService candidateService;
	
	@Autowired
	private RoomMateService roomMateService;
	
	@Autowired
	private ImageService imageService;
	
	/**
	 * Instantiates an ad object which is mapped to the spring form in 
	 * user-account.jsp
	 * 
	 * @return
	 */
	@ModelAttribute("searchAdsForm")
	public SearchAdsForm constructSearchAdsForm() {
		return new SearchAdsForm();
	}
	
	/**
	 * Instantiates an ad object which is mapped to the spring form in
	 * user-account.jsp
	 * 
	 * @return
	 */
	@ModelAttribute("ad")
	public Ad constructAd() {
		return new Ad();
	}

	/**
	 * Maps the request url /ads to the page ads.jsp and provides the model
	 * "ads" with all ads in the repository
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/ads")
	public String ads(Model model) {
		model.addAttribute("ads", adService.findAll());
		return "ads";
	}
	
	/**
	 * Maps the request url /account to the page account.jsp and provides the
	 * model "user" with the current user
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/placeAd")
	public String account(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithAds(name));
		model.addAttribute("users", userService.findAll());
		model.addAttribute("userm", messageService.findOneWithMessages(name));	
		model.addAttribute("bookmarks", bookmarkService.findAllBookmarks(name));
		model.addAttribute("candidates", candidateService.findByAdPlacer(name));

		return "placeAd";
	}
	
	@RequestMapping("/placeNewAd")
	public String placeNewAd(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithAds(name));
		model.addAttribute("users", userService.findAll());
		model.addAttribute("userm", messageService.findOneWithMessages(name));		
		model.addAttribute("bookmarks", bookmarkService.findAllBookmarks(name));
		model.addAttribute("candidates", candidateService.findByAdPlacer(name));

		return "editAd";
	}
	
	/**
	 * Receives the data from user-account form and adds the data to the ad
	 * object and saves the ad to the current user in the database. Provides
	 * model ad.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/placeNewAd", method = RequestMethod.POST)
	@Transactional
	public String doAddAd(Model model, @ModelAttribute("ad") Ad ad,
			BindingResult result,
			Principal principal,
			@RequestParam("image[]") MultipartFile[] images
			// ,@RequestParam("roomMates") String roomMate
			, org.springframework.web.context.request.WebRequest webRequest,
			@RequestParam("appointments") List<String> appointments) {

		String roomMate = webRequest.getParameter("roomMates");
		
		String name = principal.getName();
		model.addAttribute("users",userService.findAll());
		adService.save(ad, name);

		try {

			// save roommates
			if (roomMate != null) {
				saveRoomMates(ad, roomMate);
			}

			if (appointments != null) {
//				saveAppointments(ad, appointments);
			}

			// save imagesAsString
			if(!images[0].isEmpty())
			saveImages(ad, images);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/placeAd.html";
	}

	
	
	/**
	 * Removes the ad with the id={id} and redirects to account.html
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/ad/remove/{id}")
	public String removeAd(@PathVariable int id) {
		System.out.println("remove ist touched");
		Ad ad = adService.findOne(id);
		adService.delete(ad);
		return "redirect:/placeAd.html";
	}

	@RequestMapping("/ad/edit/{id}")
	public String editAd(@PathVariable int id, Model model) {
		model.addAttribute("ad", adService.findOne(id));
		model.addAttribute("users", userService.findAll());
		model.addAttribute("appointments", appointmentService.findByAd(id));
		return "editAd";
	}
	
	@RequestMapping("/ad/placeNewAd")
	public String placeNewAd() {
		return "editAd";
	}
	
	

	@RequestMapping(value = "/ad/edit/{id}", method = RequestMethod.POST)
	public String sendEdit(@PathVariable int id, Model model,
			@ModelAttribute("ad") Ad ad, BindingResult result,
			Principal principal, @RequestParam("image[]") MultipartFile[] images
			// ,@RequestParam("roomMates") String roomMate
			, org.springframework.web.context.request.WebRequest webRequest) {

		String roomMate = webRequest.getParameter("roomMates");

		String name = principal.getName();
		ad.setId(id);
		adService.save(ad, name);

		try {

			// save roommates
			if (roomMate != null) {
				saveRoomMates(ad, roomMate);
			}

			// save imagesAsString
			if (!images[0].isEmpty()) {
			saveImages(ad, images);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/account.html";
	}
	/**
	 * Maps the request url /ads to the page ads.jsp and provides the model
	 * "ads" with all ads in the repository
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchAds", method = RequestMethod.POST)
	public String search(Model model, @ModelAttribute("searchAdsForm") SearchAdsForm searchAdsForm, BindingResult result) {	
		model.addAttribute("ads", adService.findAdsWithFormCriteria(searchAdsForm.getSearchTextCity(), searchAdsForm.getSearchTextZip(), 
				searchAdsForm.getSearchTextMinPrice(), searchAdsForm.getSearchTextMaxPrice(), 
				searchAdsForm.getSearchTextNbrRoomMatesMin(),searchAdsForm.getSearchTextNbrRoomMatesMax(),
				searchAdsForm.getSearchTextNbrRoomsMin(), searchAdsForm.getSearchTextNbrRoomsMax(),
				searchAdsForm.isSearchSharedApartment()));	


		return "ads";
	}

	/**
	 * Maps the request url /ads{id} (with an ad id) to the page adDetail.jsp
	 * and provides the model "ad" with all fields of the ad
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/ads/{id}")
	@Transactional
	public String detail(Model model, @PathVariable("id") int id, Principal principal) {
		model.addAttribute("ad", adService.findOne(id));
		model.addAttribute("appointmentList", appointmentService.findByAd(id));	

		if (principal != null) {
			boolean isBkrmrkedAd = bookmarkService.isBookmarkedAd(id, principal.getName());
			model.addAttribute("isBkmrkedAd", isBkrmrkedAd);
		}

		List<RoomMate> roomMates = new ArrayList<RoomMate>();
		for (int i = 0; i < adService.findOne(id).getRoomMates().size(); i++) {
			roomMates.add(adService.findOne(id).getRoomMates().get(i));
		}
		
		model.addAttribute("roomMates", roomMates);
		
		Ad ad = adService.findOne(id);
		model.addAttribute("appointments", ad.getAppointments());
				
		for (Appointment appoint: adService.findOne(id).getAppointments()) {
			System.err.println(appoint.getId());
		}
		
		System.out.println(ad.getAppointments().size());
		appointmentService.findByAd(id);
		System.out.println("ad appointments size: " + ad.getAppointments().size());
		User user = userService.findOneByName(principal.getName());
		System.err.println("user.getAppointments size: " + user.getAppointments().size());

		return "adDetail";
	}

/*	@RequestMapping("/ad/bookmarkAd/{id}")
	public String bookmarkAd(@PathVariable int id, Principal principal) {
		Ad ad = adService.findOne(id);
		String userName = principal.getName();
		User currentUser = userService.findOneByName(userName);

		if (!userService.isBookmarkedAd(currentUser, id)) {
			userService.bookmarkAd(currentUser, ad);
		}

		return "redirect:/ads/{id}.html";
	}
	
*/
/*	@RequestMapping("/ad/unBookmarkAd/{id}")  
	public String unBookmarkAd(@PathVariable int id, Principal principal) {
		User currentUser = userService.findOneByName(principal.getName());
		userService.unBookmarkAd(currentUser, id);
		return "redirect:/ads/{id}.html";
	}
*/

	private void setModelAttributeAds(Model model,
			org.springframework.web.context.request.WebRequest webRequest) {

		// Get posted form parameters
		String searchTextCity = getSearchedCity(webRequest);
		String searchTextZip = getSearchedZIP(webRequest);
		String searchSharedApartmentAsString = getSearchForSharedApartment(webRequest);
		boolean searchSharedApartment = getSharedApartmentValue(searchSharedApartmentAsString);
		int searchTextMaxPrice = getSearchedRentPerMonthMax(webRequest);
		int searchTextMinPrice = getSearchedRentPerMonthMin(webRequest);
		int searchTextNbrRoomMatesMax = getSearchedNbrRoomMatesMax(webRequest);
		int searchTextNbrRoomMatesMin = getSearchedNbrRoomMatesMin(webRequest);
		float searchTextNbrRoomsMax = getSearchedNbrOfRoomsMax(webRequest);
		float searchTextNbrRoomsMin = getSearchedNbrOfRoomsMin(webRequest);
		model.addAttribute("ads", adService.findAdsWithFormCriteria(
				searchTextCity, searchTextZip, searchTextMinPrice,
				searchTextMaxPrice, searchTextNbrRoomMatesMin,
				searchTextNbrRoomMatesMax, searchTextNbrRoomsMin,
				searchTextNbrRoomsMax, searchSharedApartment));
	}

	private float getSearchedNbrOfRoomsMin(
			org.springframework.web.context.request.WebRequest webRequest) {
		float searchTextNbrRoomsMin = 0;
		if (!webRequest.getParameter("searchTextNbrRoomsMin").isEmpty()) {
			searchTextNbrRoomsMin = Float.parseFloat(webRequest
					.getParameter("searchTextNbrRoomsMin"));
		}
		return searchTextNbrRoomsMin;
	}

	private float getSearchedNbrOfRoomsMax(
			org.springframework.web.context.request.WebRequest webRequest) {
		// handle number of rooms
		float searchTextNbrRoomsMax = Integer.MAX_VALUE;
		if (!webRequest.getParameter("searchTextNbrRoomsMax").isEmpty()) {
			searchTextNbrRoomsMax = Float.parseFloat(webRequest
					.getParameter("searchTextNbrRoomsMax"));
		}
		return searchTextNbrRoomsMax;
	}

	private int getSearchedNbrRoomMatesMin(
			org.springframework.web.context.request.WebRequest webRequest) {
		int searchTextNbrRoomMatesMin = 0;
		if (!webRequest.getParameter("searchTextNbrRoomMatesMin").isEmpty()) {
			searchTextNbrRoomMatesMin = Integer.parseInt(webRequest
					.getParameter("searchTextNbrRoomMatesMin"));
		}
		return searchTextNbrRoomMatesMin;
	}

	private int getSearchedNbrRoomMatesMax(
			org.springframework.web.context.request.WebRequest webRequest) {
		// handle number of room mates
		int searchTextNbrRoomMatesMax = Integer.MAX_VALUE;
		if (!webRequest.getParameter("searchTextNbrRoomMatesMax").isEmpty()) {
			searchTextNbrRoomMatesMax = Integer.parseInt(webRequest
					.getParameter("searchTextNbrRoomMatesMax"));
		}
		return searchTextNbrRoomMatesMax;
	}

	private int getSearchedRentPerMonthMin(
			org.springframework.web.context.request.WebRequest webRequest) {
		int searchTextMinPrice = 0;
		if (!webRequest.getParameter("searchTextMinPrice").isEmpty()) {
			searchTextMinPrice = Integer.parseInt(webRequest
					.getParameter("searchTextMinPrice"));
		}
		return searchTextMinPrice;
	}

	private int getSearchedRentPerMonthMax(
			org.springframework.web.context.request.WebRequest webRequest) {
		// handle rent per month
		int searchTextMaxPrice = Integer.MAX_VALUE;
		if (!webRequest.getParameter("searchTextMaxPrice").isEmpty()) {
			searchTextMaxPrice = Integer.parseInt(webRequest
					.getParameter("searchTextMaxPrice"));
		}
		return searchTextMaxPrice;
	}

	private String getSearchForSharedApartment(
			org.springframework.web.context.request.WebRequest webRequest) {
		String searchSharedApartmentAsString = webRequest
				.getParameter("searchSharedApartment");
		return searchSharedApartmentAsString;
	}

	private String getSearchedZIP(
			org.springframework.web.context.request.WebRequest webRequest) {
		String searchTextZip = webRequest.getParameter("searchTextZip");
		return searchTextZip;
	}

	private String getSearchedCity(
			org.springframework.web.context.request.WebRequest webRequest) {
		String searchTextCity = webRequest.getParameter("searchTextCity");
		return searchTextCity;
	}

	private boolean getSharedApartmentValue(String searchSharedApartmentAsString) {
		boolean searchSharedApartment = true;
		if (searchSharedApartmentAsString != null
				&& !searchSharedApartmentAsString.equals("on")) {
			searchSharedApartment = false;
		}
		return searchSharedApartment;
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
			if(!imageMPF.isEmpty()){
				bytes = imageMPF.getBytes();
				byte[] encoded = Base64.encodeBase64(bytes);
				String encodedString = new String(encoded);
				image.setImageAsString(encodedString);
				image.setAd(ad);
				imageService.save(image);
			}

		}
	}


	
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
