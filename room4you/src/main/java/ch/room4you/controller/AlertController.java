package ch.room4you.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.room4you.entity.Alert;
import ch.room4you.entity.User;
import ch.room4you.service.AlertService;
import ch.room4you.service.UserService;

@Controller
public class AlertController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AlertService alertService;


	/**
	 * Instantiates a new User and maps the values from spring form in subscribeAlerts.jsp 
	 * to the new user object (with commandName="user" in spring form)
	 * @return User
	 */
	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}
	
	/**
	 * Instantiates a new alert and maps the values from spring form in subscribeAlerts.jsp 
	 * to the new user object (with commandName="user" in spring form)
	 * @return Alert
	 */
	@ModelAttribute("alert")
	public Alert constructAlert() {
		return new Alert();
	}

	/**
	 * Maps the request url /register to the page subscribeAlerts.jsp
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String showAlert() {
		return "alert";
	}
	
	/**
	 * Receives the data from user-register form and adds the data to the
	 * user object and saves the user in the database.
	 * If successful, show success notification on register page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/alert", method = RequestMethod.POST)
	public String subscribeAlerts(@ModelAttribute("alert") Alert alert, Principal principal, BindingResult result) {
		if (result.hasErrors()) {
			return "alert";
		}
		String name = principal.getName();
		alertService.save(alert, name);
		return "redirect:/subscribeAlerts.html?success=true";
	}
	
//	
//	
//	/**
//	 * Maps the request url /ads to the page ads.jsp and provides the model "ads"
//	 * with all ads in the repository
//	 * 
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value="/searchAlertedAds", method = RequestMethod.POST)
//	public String search(Model model, org.springframework.web.context.request.WebRequest webRequest) {	
//		
//			//Get posted form parameters
//			String searchTextCity = webRequest.getParameter("searchTextCity");
//			String searchTextZip= webRequest.getParameter("searchTextZip");
//			String searchSharedApartmentAsString = webRequest.getParameter("searchSharedApartment");
//			
//			//handle sharedApartment checkbox
//			boolean searchSharedApartment = false;
//			if(searchSharedApartmentAsString!=null && searchSharedApartmentAsString.equals("on")){
//				searchSharedApartment = true;
//			}
//			
//			//handle rent per month
//			int searchTextMaxPrice = Integer.MAX_VALUE;
//			if(!webRequest.getParameter("searchTextMaxPrice").isEmpty()){
//				searchTextMaxPrice = Integer.parseInt(webRequest.getParameter("searchTextMaxPrice"));
//			}
//
//			int searchTextMinPrice = 0;
//			if(!webRequest.getParameter("searchTextMinPrice").isEmpty()){
//				searchTextMinPrice = Integer.parseInt(webRequest.getParameter("searchTextMinPrice"));
//			}
//			
//			//handle number of room mates
//			int searchTextNbrRoomMatesMax = Integer.MAX_VALUE;
//			if(!webRequest.getParameter("searchTextNbrRoomMatesMax").isEmpty()){
//				searchTextNbrRoomMatesMax = Integer.parseInt(webRequest.getParameter("searchTextNbrRoomMatesMax"));
//			}
//
//			int searchTextNbrRoomMatesMin = 0;
//			if(!webRequest.getParameter("searchTextNbrRoomMatesMin").isEmpty()){
//				searchTextNbrRoomMatesMin = Integer.parseInt(webRequest.getParameter("searchTextNbrRoomMatesMin"));
//			}
//			
//			//handle number of rooms
//			float searchTextNbrRoomsMax = Integer.MAX_VALUE;
//			if(!webRequest.getParameter("searchTextNbrRoomsMax").isEmpty()){
//				searchTextNbrRoomsMax = Integer.parseInt(webRequest.getParameter("searchTextNbrRoomsMax"));
//			}
//
//			float searchTextNbrRoomsMin = 0;
//			if(!webRequest.getParameter("searchTextNbrRoomsMin").isEmpty()){
//				searchTextNbrRoomsMin = Integer.parseInt(webRequest.getParameter("searchTextNbrRoomsMin"));
//			}
//
//		model.addAttribute("ads", adService.findAdsWithFormCriteria(searchTextCity, searchTextZip, 
//				searchTextMinPrice, searchTextMaxPrice, 
//				searchTextNbrRoomMatesMin,searchTextNbrRoomMatesMax,
//				searchTextNbrRoomsMin, searchTextNbrRoomsMax,
//				searchSharedApartment));
//		return "ads";
//	}
//	
//	/**
//	 * Maps the request url /ads{id} (with an ad id) to the page adDetail.jsp and provides the model "ad"
//	 * with all fields of the ad
//	 * 
//	 * @param model
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/ads/{id}")
//	public String detail(Model model, @PathVariable int id) {
//		model.addAttribute("ad", adService.findOne(id));
//		
//		List<RoomMate> roomMates = new ArrayList<RoomMate>();
//		for(int i = 0; i < adService.findOne(id).getRoomMates().size(); i++){
//			roomMates.add(adService.findOne(id).getRoomMates().get(i));			
//		}
//		
//		model.addAttribute("roomMates", roomMates); 
//
//		return "adDetail";
//	}
	
	
}
