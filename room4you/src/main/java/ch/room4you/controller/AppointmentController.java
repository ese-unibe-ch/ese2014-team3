package ch.room4you.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Appointment;
import ch.room4you.entity.Application;
import ch.room4you.entity.Message;
import ch.room4you.entity.User;
import ch.room4you.service.AdService;
import ch.room4you.service.ApplicationService;
import ch.room4you.service.AppointmentService;
import ch.room4you.service.MessageService;
import ch.room4you.service.UserService;

@Controller
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private AdService adService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@Autowired
	private ApplicationService applicationService;

	@ModelAttribute("application")
	public Application constructApplication() {
		return new Application();
	}

	@RequestMapping("/ad/{adId}/appointment/{id}")
	public String application(Model model, @PathVariable("adId") int adId,
			@PathVariable("id") int id, Principal principal) {
		if (principal != null) {
			Ad ad = adService.findOne(adId);
			model.addAttribute("ad", ad);
			model.addAttribute("sender",
					userService.findOneByName(principal.getName()));
			model.addAttribute("recipient", ad.getUser());
			model.addAttribute("appointment", appointmentService.findOne(id));
			return "application";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "ad/{adId}/appointment/{id}", method = RequestMethod.POST)
	public String sendApplication(Model model, @PathVariable("id") int id
			, @PathVariable("adId") int adId
			, @ModelAttribute("application") Application application
			, BindingResult result, Principal principal) {
	
		Ad ad = adService.findOne(adId);
		User sender = userService.findOneByName(principal.getName());
		Appointment appointment = appointmentService.findOne(id);


		application.setSender(sender);
		application.setRecipient(ad.getUser());
		application.setTitle("Appointment Application for Ad: " + ad.getTitle());
		application.setAppointment(appointment);
		application.setApplicationAd(ad);

		applicationService.save(application);

		return "redirect:/ads/{adId}.html";
	}
	
	@RequestMapping("/showApplication/${id}")
	public String showApplication(Model model, @PathVariable("id") int id) {
		model.addAttribute("application", applicationService.findOne(id));
		return "showApplication";
	}

}
