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
import ch.room4you.service.UserService;


@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private AdService adService;
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("newmessage")
	public Message constructMessage() {
		return new Message();
	}
	
	@RequestMapping("/message/{id}")
	public String message(Model model, @PathVariable int id, Principal principal) {
		if (principal != null) {
			model.addAttribute("ad", adService.findOne(id));
			model.addAttribute("principal", principal.getName());
			return "message";
		}
		else {
			return "login";
		}
	}
	
	@RequestMapping("/showmessage/{id}")
	public String showMessage(Model model, @PathVariable int id) {
		model.addAttribute("message", messageService.findOne(id));
		return "showmessage";
	}
	
	@RequestMapping("/reply/{ad}/{snd}/{rcp}")
	public String reply(Model model, @PathVariable("ad") int ad, @PathVariable("snd") int snd, @PathVariable("rcp") int rcp) {
		model.addAttribute("ad", adService.findOne(ad));
		model.addAttribute("sender", userService.findOne(snd));
		model.addAttribute("recipient", userService.findOne(rcp));
		return "message";
	}
	
	@RequestMapping(value="/message/{id}", method = RequestMethod.POST)
	public String sendMessage(@Valid @ModelAttribute("newmessage") Message message, BindingResult result, @PathVariable int id, Principal principal) {
		if (result.hasErrors()) {
			return "message";
		}
		message.setRecipient(adService.findOne(id).getUser());
		message.setSender(userService.findOneByName(principal.getName()));
		message.setMessageAd(adService.findOne(id));
		messageService.save(message);
		return "redirect:/ads/{id}.html?success=true";
	}
}