package ch.room4you.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.room4you.service.AdService;

@Controller
public class AdController {
	
	@Autowired
	private AdService adService;

	@RequestMapping("/ads")
	public String ads(Model model) {
		model.addAttribute("ads", adService.findAll());
		return "ads";
	}
}
