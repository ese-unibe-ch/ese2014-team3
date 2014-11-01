package ch.room4you.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.room4you.entity.Ad;

import ch.room4you.entity.RoomMate;
import ch.room4you.entity.User;
import ch.room4you.service.AdService;
import ch.room4you.service.UserService;

@Controller
public class AdController {
	
	@Autowired
	private AdService adService;
	
	@Autowired
	private UserService userService;

	/**
	 * Maps the request url /ads to the page ads.jsp and provides the model "ads"
	 * with all ads in the repository
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
	 * Maps the request url /ads to the page ads.jsp and provides the model "ads"
	 * with all ads in the repository
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchAds", method = RequestMethod.POST)
	public String search(Model model, org.springframework.web.context.request.WebRequest webRequest) {	
		
			//Get posted form parameters
			String searchTextCity = webRequest.getParameter("searchTextCity");
			String searchTextZip= webRequest.getParameter("searchTextZip");
			String searchSharedApartmentAsString = webRequest.getParameter("searchSharedApartment");
			
			//handle sharedApartment checkbox
			boolean searchSharedApartment = true;
			if(searchSharedApartmentAsString!=null && !searchSharedApartmentAsString.equals("on")){
				searchSharedApartment = false;
			}
			
			//handle rent per month
			int searchTextMaxPrice = Integer.MAX_VALUE;
			if(!webRequest.getParameter("searchTextMaxPrice").isEmpty()){
				searchTextMaxPrice = Integer.parseInt(webRequest.getParameter("searchTextMaxPrice"));
			}

			int searchTextMinPrice = 0;
			if(!webRequest.getParameter("searchTextMinPrice").isEmpty()){
				searchTextMinPrice = Integer.parseInt(webRequest.getParameter("searchTextMinPrice"));
			}
			
			//handle number of room mates
			int searchTextNbrRoomMatesMax = Integer.MAX_VALUE;
			if(!webRequest.getParameter("searchTextNbrRoomMatesMax").isEmpty()){
				searchTextNbrRoomMatesMax = Integer.parseInt(webRequest.getParameter("searchTextNbrRoomMatesMax"));
			}

			int searchTextNbrRoomMatesMin = 0;
			if(!webRequest.getParameter("searchTextNbrRoomMatesMin").isEmpty()){
				searchTextNbrRoomMatesMin = Integer.parseInt(webRequest.getParameter("searchTextNbrRoomMatesMin"));
			}
			
			//handle number of rooms
			float searchTextNbrRoomsMax = Integer.MAX_VALUE;
			if(!webRequest.getParameter("searchTextNbrRoomsMax").isEmpty()){
				searchTextNbrRoomsMax = Float.parseFloat(webRequest.getParameter("searchTextNbrRoomsMax"));
			}

			float searchTextNbrRoomsMin = 0;
			if(!webRequest.getParameter("searchTextNbrRoomsMin").isEmpty()){
				searchTextNbrRoomsMin = Float.parseFloat(webRequest.getParameter("searchTextNbrRoomsMin"));
			}

		model.addAttribute("ads", adService.findAdsWithFormCriteria(searchTextCity, searchTextZip, 
				searchTextMinPrice, searchTextMaxPrice, 
				searchTextNbrRoomMatesMin,searchTextNbrRoomMatesMax,
				searchTextNbrRoomsMin, searchTextNbrRoomsMax,
				searchSharedApartment));

		return "ads";
	}
	
	/**
	 * Maps the request url /ads{id} (with an ad id) to the page adDetail.jsp and provides the model "ad"
	 * with all fields of the ad
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/ads/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("ad", adService.findOne(id));
		
		List<RoomMate> roomMates = new ArrayList<RoomMate>();
		for(int i = 0; i < adService.findOne(id).getRoomMates().size(); i++){
			roomMates.add(adService.findOne(id).getRoomMates().get(i));			
		}
		
		model.addAttribute("roomMates", roomMates); 

		return "adDetail";
	}
	
	@RequestMapping("/ad/bookmarkAd/{id}")
	public String bookmarkAd(@PathVariable int id) {
		Ad ad = adService.findOne(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = auth.getName();
		User user = userService.findOneByName(currentUser);
	//	System.out.println(user.isBookmarkedAd(ad));
	//	System.out.println(!user.isBookmarkedAd(ad));
		
		//contains method doesn't work in isBookmarked
		if (!user.isBookmarkedAd(ad)) {
			user.setBookmarkedAd(ad);
			userService.save(user);
		 }
		return "redirect:/ads/{id}.html";
	}
	
}
