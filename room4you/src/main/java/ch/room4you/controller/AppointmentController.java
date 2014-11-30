package ch.room4you.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.room4you.service.AppointmentService;
import ch.room4you.entity.User;



@Controller
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;



	@RequestMapping(value="/ad/{adId}/appointment/{appointId}")
	public String addUserToAppointment(Principal principal, @PathVariable("adId") int adId
			, @PathVariable("appointId") int appointId) {
		
		appointmentService.addVisitor(appointId, principal.getName());
		return "redirect:/ads/{adId}.html";
	}
	
	@RequestMapping(value="/promisingCandidates")
	public String compileCandidates(@RequestParam("candidates") List<User> candidates) {
		
		System.out.println(candidates);
		appointmentService.compileCandidates(candidates);
		return "redirect:/account.html";
	}
	
	
	}

	
