package ch.room4you.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
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
	
	Logger  log = Logger.getLogger(AdController.class);

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
		
		System.out.println(userService.findOneByName(principal.getName()).getFavCandidates().isEmpty());

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
			 ,@RequestParam(value="roomMates", defaultValue="anonymous") List<String> roomMate
			, org.springframework.web.context.request.WebRequest webRequest,
			@RequestParam("appointments") List<String> appointments) {
		log.info("Got following roommate string:"+ roomMate);
		System.out.println("Got following roommate string:"+ roomMate);
		
		model.addAttribute("users",userService.findAll());
		
		adService.doAddAd(model, ad, result, principal, images, roomMate, webRequest, appointments);

		return "redirect:/placeAd.html"; 
	}
	
	@RequestMapping(value="/compile/{id}", method = RequestMethod.POST)
	public String compileCandidates(Model model /*, BindingResult result */, Principal principal,
			@PathVariable("id") int id, @RequestParam("candidates") List<Integer> candidates) {
		
		candidateService.createFavList(candidates, id, principal.getName());
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
		Ad ad = adService.findOne(id);
		adService.delete(ad);
		return "redirect:/placeAd.html";
	}

	@RequestMapping("/ad/edit/{id}")
	public String editAd(@PathVariable int id, Model model) {
		model.addAttribute("ad", adService.findOne(id));
		model.addAttribute("users", userService.findAll());
		model.addAttribute("appointments", appointmentService.findByAd(id));
		model.addAttribute("selectedRoomMates", roomMateService.findRoomMatesForAd(adService.findOne(id)));
		model.addAttribute("selectedImages", adService.findOne(id).getImages());
		return "editAd";
	}
	
	@RequestMapping("/ad/deleteRoomMate/{id}/{rmId}")
	public String deleteRoomMates(@PathVariable int id, @PathVariable int rmId,  Model model) {
		List<RoomMate> rm = roomMateService.findRoomMatesForAd(adService.findOne(id));		
		for(RoomMate roomMate : rm){
			if(roomMate.getUser().getId()==rmId){
				roomMateService.deleteRoomMate(roomMate);
			}
		}
		return "redirect:/ad/edit/"+id+".html"; 
	}
	
	@RequestMapping("/ad/deleteImage/{id}/{imageId}")
	public String deleteImage(@PathVariable int id,@PathVariable int imageId, Model model) {
		Image image = imageService.findOneById(imageId);
		imageService.delete(image);
		
		return "redirect:/ad/edit/"+id+".html"; 
	}
	
	@RequestMapping("/ad/placeNewAd")
	public String placeNewAd() {
		return "editAd";
	}
	
	

	@RequestMapping(value = "/ad/edit/{id}", method = RequestMethod.POST)
	public String sendEdit(@PathVariable int id, Model model,
			@ModelAttribute("ad") Ad ad, BindingResult result,
			Principal principal, @RequestParam("image[]") MultipartFile[] images
			 ,@RequestParam(value="roomMates", defaultValue="anonymous") List<String> roomMate
			, org.springframework.web.context.request.WebRequest webRequest
			, @RequestParam("appointments") List<String> appointments) {
		adService.editAd(id, model, ad, result, principal, images, roomMate, webRequest, appointments);
	
		return "redirect:/placeAd.html";
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
		
		appointmentService.findByAd(id);
		if(principal.getName() !=null){
			User user = userService.findOneByName(principal.getName());
			log.debug("user.getAppointments size: " + user.getAppointments().size());
		}

		return "adDetail";
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
