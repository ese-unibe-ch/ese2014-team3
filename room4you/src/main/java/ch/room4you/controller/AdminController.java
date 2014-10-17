package ch.room4you.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.room4you.service.UserService;

@Controller
@RequestMapping("/users")
public class AdminController {

	@Autowired
	private UserService userService;

	@RequestMapping
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}

	@RequestMapping("/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.findOneById(id));
		return "user-detail";
	}
	
//	@RequestMapping("/{id}")
//	public String userProfile(Model model, @PathVariable int id) {
//		model.addAttribute("userProfile", userService.findOneWithAds(id));
//		return "userProfile";
//	}

	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/users.html";
	}

}
