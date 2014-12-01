package ch.room4you.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.room4you.service.AppointmentService;
import ch.room4you.entity.FavCandidates;
import ch.room4you.entity.User;



@Controller
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;

	@ModelAttribute
	public FavCandidates constructFavCandidates(){
		return new FavCandidates();
	}

	@RequestMapping(value="/ad/{adId}/appointment/{appointId}")
	public String addUserToAppointment(Principal principal, @PathVariable("adId") int adId
			, @PathVariable("appointId") int appointId) {
		
		appointmentService.addVisitor(appointId, principal.getName());
		return "redirect:/ads/{adId}.html?addedToAppointment=true";
	}
	
	@RequestMapping(value="compileCandidates", method = RequestMethod.POST)
	public String compileCandidates(Model model,  @ModelAttribute("favCandidates") FavCandidates favCandidates,
			BindingResult result, Principal principal) {
		
		appointmentService.compileCandidates(favCandidates, principal.getName()/*, adId */);
		return "redirect:/account.html";
	}
	
	
	}

	
