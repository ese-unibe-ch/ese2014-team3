package ch.room4you.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.room4you.service.AdService;

@Controller
public class IndexController {
	
	@Autowired
	private AdService adService;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
//		model.addAttribute("ads", adService.);
		return "index";
	}
}
