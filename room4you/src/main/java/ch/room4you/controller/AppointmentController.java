package ch.room4you.controller;

import java.security.Principal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.room4you.service.AppointmentService;



@Controller
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;



	@RequestMapping(value="/ad/{adId}/appointment/{appointId}")
	@Transactional
	public String addUserToAppointment(Principal principal, @PathVariable("adId") int adId
			, @PathVariable("appointId") int appointId) {
		
		appointmentService.addVisitor(appointId, principal.getName());
		return "redirect:/ads/{adId}.html";
	}
	
	
	}

	
