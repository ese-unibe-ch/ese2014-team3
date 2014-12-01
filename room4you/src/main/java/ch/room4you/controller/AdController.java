package ch.room4you.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pojo.SearchAdsForm;
import ch.room4you.entity.Ad;
import ch.room4you.entity.Appointment;
import ch.room4you.entity.RoomMate;
import ch.room4you.entity.User;
import ch.room4you.service.AdService;
import ch.room4you.service.BookmarkService;
import ch.room4you.service.UserService;

@Controller
public class AdController {

	@Autowired
	private AdService adService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookmarkService bookmarkService;
	
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

}
