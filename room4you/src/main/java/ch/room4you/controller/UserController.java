package ch.room4you.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
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
import ch.room4you.entity.FavCandidates;
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
