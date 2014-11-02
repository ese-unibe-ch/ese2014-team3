package ch.room4you.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.room4you.service.UserService;

/**
 * Maps the request url /users
 * 
 * @author Sven
 *
 */
@Controller
@RequestMapping("/users")
public class AdminController {

	/**
	 * Instantiates a userService
	 */
	@Autowired
	private UserService userService;

	

	/**
	 * Maps the request url /users to the page users.jsp and provides the model "users"
	 * with all users in the repository
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}

	/**
	 * Maps the request url /users{id} to the page users-detail.jsp and provides the model "user"
	 * with all fields of the user object
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.findOneById(id));
		return "user-detail";
	}
	

	/**
	 * Removes the user with id = {id} and redirects to users.html
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/users.html";
	}

}
