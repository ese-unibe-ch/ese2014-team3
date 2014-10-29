package ch.room4you.controller;

import java.security.Principal;

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
import ch.room4you.service.AdService;
import ch.room4you.service.MessageService;


@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private AdService adService;
	
	@ModelAttribute("newmessage")
	public Message constructMessage() {
		return new Message();
	}
	
	@RequestMapping("/message/{id}")
	public String message(Model model, @PathVariable int id, Principal principal) {
		model.addAttribute("ad", adService.findOne(id));
		model.addAttribute("principal", principal.getName());
		return "message";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String sendMessage(@Valid @ModelAttribute("newmessage") Message message, BindingResult result, @PathVariable int id) {
		if (result.hasErrors()) {
			return "message";
		}
		messageService.save(message);
		return "redirect:/message/{id}.html?success=true";
	}
}