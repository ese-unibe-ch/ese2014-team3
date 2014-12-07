package ch.room4you.controller;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.room4you.entity.Message;
import ch.room4you.service.AdService;
import ch.room4you.service.MessageService;

@Controller
public class IndexController {
	
	@Autowired
	private AdService adService;
	
	@Autowired
	private MessageService messageService;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model, Principal principal) {
		if(principal!=null){
		List<Message> messages = messageService.findNbrOfUnreadMessages(principal.getName());
		model.addAttribute("nbrUnreadMessages", messages.size());
		}
		return "index";
	}
	
}
