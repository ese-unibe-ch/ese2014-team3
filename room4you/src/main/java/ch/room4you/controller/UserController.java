package ch.room4you.controller;


import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import ch.room4you.entity.Image;
import ch.room4you.entity.RoomMate;
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
//			,@RequestParam("roomMates") int[] roomMatesIds
			) {


		if (result.hasErrors()) {		
			return account(model, principal);
		}else{
					
            
			byte[] bytes;
			
			try {
				String name = principal.getName();
				
				adService.save(ad, name);
				
				System.out.println("roomMate size: " );
				
				//save images
				for(MultipartFile imageMPF : images){
					Image image = new Image();
					bytes = imageMPF.getBytes();
					image.setImage(bytes);
					image.setAd(ad);
					imageService.save(image);
				}
				
				//save roommates
//				for(int id : roomMatesIds){
//				      RoomMate rm = new RoomMate();
//				      rm.setUser(userService.findOne(id));
//				      System.out.println(rm.getUser().getName());
//				      rm.setAd(ad);
//				      roomMateService.save(rm, ad.getId());
//				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
	
	
//	/**
//	 * Removes the ad with the id={id} and redirects to account.html
//	 * 
//	 * @param model
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/ad/add/{id}")
//	public String addAdImage(BindingResult result,
//			@PathVariable int id, @RequestParam("name") String name,
//            @RequestParam("file") MultipartFile file) {
//		Ad ad = adService.findOne(id);
//		System.out.println("/ad/add/ touched");
//		  if (!file.isEmpty()) {
//	            try {
//	                byte[] bytes = file.getBytes();
//	                BufferedOutputStream stream =
//	                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
//	                stream.write(bytes);
//	                stream.close();
//	                System.out.println("Got image:"+ name + id);
////	            	ad.addImage(image);
////	                image.setAd(ad);
//	        		return "redirect:/account.html";
//	            } catch (Exception e) {
//	                return "You failed to upload " + name + " => " + e.getMessage();
//	            }
//	        } else {
//	            return "You failed to upload " + name + " because the file was empty.";
//	        }
//	    }
//	
//    @RequestMapping(value="/upload", method=RequestMethod.GET)
//    public @ResponseBody String provideUploadInfo() {
//        return "You can upload a file by posting to this same URL.";
//    }
//    
//  
//
//    @RequestMapping(value="/upload", method=RequestMethod.POST)
//    public String handleFileUpload(Model model, @RequestParam("name") String name,
//            @RequestParam("file") MultipartFile file, @PathVariable int adId){
//    	
////    	Image image = new Image();
//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream stream =
//                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
//                stream.write(bytes);
//                stream.close();
//                model.addAttribute("name", name);
////                image.setImage(bytes);
////                image.setName(name);
//                System.out.println("Got image:"+ name + adId);
//            //	Ad ad = adService.findOne(id);
//            //	ad.addImage(image);
//            //    image.setAd(ad);
////        		imageService.save(image);
//        		return "redirect:/account.html";
//            } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
//            }
//        } else {
//            return "You failed to upload " + name + " because the file was empty.";
//        }
//    }
	
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
