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
	
	
	/**
	 * Maps the request url /account to the page account.jsp and provides the
	 * model "user" with the current user
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/messages")
	public String account(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithAds(name));
		model.addAttribute("users", userService.findAll());
		model.addAttribute("userm", userService.findOneWithMessages(name));

		return "messages";
	}
	
	@RequestMapping("/message/{id}")
	public String message(Model model, @PathVariable int id, Principal principal) {
		if (principal != null) {
			model.addAttribute("ad", adService.findOne(id));
			model.addAttribute("sender", userService.findOneByName(principal.getName()));
			model.addAttribute("recipient", adService.findOne(id).getUser());
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
	
	@RequestMapping("/reply/{id}")
	public String reply(Model model, @PathVariable int id) {
		Message message = messageService.findOne(id);
		model.addAttribute("ad", adService.findOne(message.getMessageAd().getId()));
		model.addAttribute("sender", message.getRecipient());
		model.addAttribute("recipient", message.getSender());
		return "message";
	}
	
	@RequestMapping(value="/reply/{id}", method = RequestMethod.POST)
	public String sendReply(@Valid @ModelAttribute("newmessage") Message reply, BindingResult result, @PathVariable int id) {
		if (result.hasErrors()) {
			return "message";
		}
		Message message = messageService.findOne(id);
		reply.setRecipient(message.getSender());
		reply.setSender(message.getRecipient());
		reply.setMessageAd(message.getMessageAd());
		reply.setId(null);
		messageService.save(reply);
		return "redirect:/account.html?success=true";
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
	
	@RequestMapping("/message/remove/{id}")
	public String removeMessage(@PathVariable int id) {
		messageService.delete(id);
		return "redirect:/account.html";
	}
}