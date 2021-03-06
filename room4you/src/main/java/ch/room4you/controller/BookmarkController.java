package ch.room4you.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.room4you.service.BookmarkService;

@Controller
public class BookmarkController {
	
	@Autowired
	private BookmarkService bookmarkService;
	
	/**
	 * 
	 * 
	 * @param AdId
	 * @param principal
	 * @return redirects to the bookmarked ad
	 */
	@RequestMapping("/ad/bookmarkAd/{id}")
	public String bookmarkAd(@PathVariable int id, Principal principal) {
		bookmarkService.bookmarkAd(id, principal.getName());
		return "redirect:/ads/{id}.html";
	}
	
	/**
	 * 
	 * @param AdId
	 * @param principal
	 * @return redirects to the unbookmarked ad
	 */
	@RequestMapping("/ad/unBookmarkAd/{id}")  
	public String unBookmarkAd(@PathVariable int id, Principal principal) {
		bookmarkService.unBookmarkAd(id, principal.getName());
		return "redirect:/ads/{id}.html";
	}
	
	/**
	 * 
	 * @param AdId
	 * @param principal
	 * @return rediretcs to the user's account
	 */
	@RequestMapping("/ad/removeBookmarkedAd/{id}")
	public String removeBookmarkedAd(@PathVariable int id, Principal principal) {
		bookmarkService.unBookmarkAd(id, principal.getName());
		return "redirect:/placeAd.html";
	}
}
