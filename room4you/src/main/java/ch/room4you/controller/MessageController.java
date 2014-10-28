package ch.room4you.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.room4you.entity.Message;
import ch.room4you.entity.User;
import ch.room4you.service.AdService;


@Controller
public class MessageController {
	
	@Autowired
	private AdService adService;
	
	@ModelAttribute("message")
	public Message constructMessage() {
		return new Message();
	}
	
	@RequestMapping("/message/{id}")
	public String message(Model model, @PathVariable int id) {
		model.addAttribute("ad", adService.findOne(id));
		return "message";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String sendMessage(@Valid @ModelAttribute("message") Message message, BindingResult result, @PathVariable int id) {
		if (result.hasErrors()) {
			return "message";
		}
		return "redirect:/{id}.html?success=true";
	}
}