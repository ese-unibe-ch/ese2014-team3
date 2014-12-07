package ch.room4you.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.room4you.service.AppointmentService;
import ch.room4you.service.FavCandidatesService;



@Controller
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private FavCandidatesService candService;


	@RequestMapping(value="/ad/{adId}/appointment/{appointId}")
	public String addUserToAppointment(Principal principal, @PathVariable("adId") int adId
			, @PathVariable("appointId") int appointId) {
		
		appointmentService.addVisitor(appointId, principal.getName());
		return "redirect:/ads/{adId}.html";
	}
	
	
	@RequestMapping("/ad/{adId}/deleteAppointment/{id}") 
		public String deleteAppointment(@PathVariable("appointId") int appointId, @PathVariable("adId") int adId) {
			appointmentService.delteAppointment(appointId);
			return "redirect:/editAd/{adId}.html";
		}
	
	@RequestMapping("/deleteFavList/{listId}") 
		public String deleteFavCandidatesList(@PathVariable("listId") int listId) {
			candService.delete(listId);
			return "redirect:/placeAd";
		}
	}

	
	
	

	
