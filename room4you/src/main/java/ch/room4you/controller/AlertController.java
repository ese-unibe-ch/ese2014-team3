package ch.room4you.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.room4you.entity.Alert;
import ch.room4you.entity.User;
import ch.room4you.service.AlertService;
import ch.room4you.service.UserService;

@Controller
public class AlertController {
	
	static Logger log = Logger.getLogger(
            AlertController.class.getName());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AlertService alertService;


	/**
	 * Instantiates a new User and maps the values from spring form in subscribeAlerts.jsp 
	 * to the new user object (with commandName="user" in spring form)
	 * @return User
	 */
	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}
	
	/**
	 * Instantiates a new alert and maps the values from spring form in subscribeAlerts.jsp 
	 * to the new user object (with commandName="user" in spring form)
	 * @return Alert
	 */
	@ModelAttribute("alert")
	public Alert constructAlert() {
		return new Alert();
	}

	/**
	 * Maps the request url /register to the page subscribeAlerts.jsp
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String showAlert() {
		return "alert";
	}
	
	/**
	 * Receives the data from user-register form and adds the data to the
	 * user object and saves the user in the database.
	 * If successful, show success notification on register page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/alert", method = RequestMethod.POST)
	public String subscribeAlerts(@ModelAttribute("alert") Alert alert, Principal principal, BindingResult result) {
		if (result.hasErrors()) {
			return "alert";
		}
		String name = principal.getName();
		alertService.save(alert, name);
		
		return "redirect:/subscribeAlerts.html?success=true";
	}
	
	/**
	 * Removes the alert with id = {id} and redirects to account.html
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/alert/remove/{id}")
	public String removeAlert(@PathVariable int id) {
		alertService.delete(id);
		return "redirect:/account.html";
	}	

}
