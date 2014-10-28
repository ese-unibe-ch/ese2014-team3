package ch.room4you.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ch.room4you.entity.RoomMate;
import ch.room4you.service.AdService;

@Controller
public class AdController {
	
	@Autowired
	private AdService adService;

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
	
	
}
