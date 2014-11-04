package ch.room4you.controller;


import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Image;
import ch.room4you.entity.RoomMate;
import ch.room4you.entity.User;
import ch.room4you.service.AdService;
import ch.room4you.service.ImageService;
import ch.room4you.service.RoomMateService;
import ch.room4you.service.UserService;

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
	 * Maps the request url /account to the page account.jsp and provides the model "user"
	 * with the current user 
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithAds(name));
		model.addAttribute("users", userService.findAll());
		model.addAttribute("userm", userService.findOneWithMessages(name));
		return "account";
	}

	/**
	 * Receives the data from user-account form and adds the data to the
	 * ad object and saves the ad to the current user in the database.
	 * Provides model ad.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAddAd(Model model, @ModelAttribute("ad") Ad ad, BindingResult result, 
			Principal principal, @RequestParam("image[]") MultipartFile[] images
	//		,@RequestParam("roomMates") String roomMate
			,org.springframework.web.context.request.WebRequest webRequest
			) {	
		
			String roomMate = webRequest.getParameter("roomMates");
				
			String name = principal.getName();			
			adService.save(ad, name);     
			
			try {
				
				//save roommates
				if(roomMate!=null){
					saveRoomMates(ad, roomMate);
				}		
				
				//save imagesAsString
				saveImages(ad, images);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return "redirect:/account.html";
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
	
	@RequestMapping("/ad/unBookmarkAd/{id}")  
	public String unBookmarkAd(@PathVariable int id, Principal principal) {
		String userName = principal.getName();
		User user = userService.findOneByName(userName);
		userService.unBookmarkAd(user, id);
		return "redirect:/account.html";
	}
	

	private void saveRoomMates(Ad ad, String roomMate) {
		List<String> roomMates = Arrays.asList(roomMate.split(","));
		for(String roomM : roomMates){
			RoomMate rm = new RoomMate();
			rm.setUser(userService.findOne(Integer.parseInt(roomM)));
			rm.setAd(ad);	      
			roomMateService.save(rm);
		}
	}
	
	private void saveImages(Ad ad, MultipartFile[] images) throws IOException {
		byte[] bytes;
		for(MultipartFile imageMPF : images){
			Image image = new Image();
			bytes = imageMPF.getBytes();
			byte[] encoded=Base64.encodeBase64(bytes);
			String encodedString = new String(encoded);
			image.setImageAsString(encodedString);
			image.setAd(ad);
			imageService.save(image);
		}
	}
	
	/**
	 * Maps the date format to the convenient date format for the database
	 * @param binder
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
