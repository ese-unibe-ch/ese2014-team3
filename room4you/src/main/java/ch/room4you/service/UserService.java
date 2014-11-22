package ch.room4you.service;

/**
 * Database operation service for userRepository interface
 */
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Application;
import ch.room4you.entity.Message;
import ch.room4you.entity.Role;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.ApplicationRepository;
import ch.room4you.repository.MessageRepository;
import ch.room4you.repository.RoleRepository;
import ch.room4you.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AdRepository adRepository;

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ApplicationRepository applicationRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithAds(int id) {
		User user = findOne(id);
		List<Ad> ads = adRepository.findByUser(user);
		user.setAds(ads);
		return user;
	}

	public User findOneWithMessages(String name) {
		User user = userRepository.findByName(name);
		List<Message> messages = messageRepository.findByRecipient(user);
		List<Message> sentMessages = messageRepository.findBySender(user);
		user.setMessages(messages);
		user.setSentMessages(sentMessages);
		return user;
	}

	public User findOneWithAds(String name) {
		User user = userRepository.findByName(name);
		return findOneWithAds(user.getId());
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);

		userRepository.save(user);
	}

	public void delete(int id) {
		userRepository.delete(id);
	}

	public User findOneByName(String username) {
		return userRepository.findByName(username);
	}

	public User findOneById(int id) {
		User user = findOne(id);
		List<Ad> ads = adRepository.findByUser(user);
		user.setAds(ads);
		return user;
	}

	public void bookmarkAd(User user, Ad ad) {
		user.setBookmarkedAd(ad);
		userRepository.save(user);
	}

	public boolean isBookmarkedAd(User user, int adId) {
		List<Ad> bookmarkedAds = user.getBookmarkedAds();
		boolean isBookmarkedAd = false;
		for (Ad a : bookmarkedAds) {
			if (a.getId() == adId)
				isBookmarkedAd = true;
		}
		return isBookmarkedAd;
	}

	public void unBookmarkAd(User user, int adId) {
		List<Ad> bookmarkedAds = user.getBookmarkedAds();
		List<Ad> updatedBookmarks = removeBookmarkAdById(bookmarkedAds, adId);
		user.setBookmarkedAds(updatedBookmarks);
		userRepository.save(user);
	}

	public List<Ad> removeBookmarkAdById(List<Ad> bookmarkedAds, int adId) {
		int size = bookmarkedAds.size();
		for (int i = 0; i < size; i++) {
			Ad a = bookmarkedAds.get(i);
			if (a.getId() == adId)
				bookmarkedAds.remove(a);
		}
		return bookmarkedAds;
	}

	public User findOneWithApplications(String name) {
		User user = userRepository.findByName(name);
		List<Application> sentApplications = applicationRepository.findBySender(user);
		List<Application> receivedApplications = applicationRepository.findByRecipient(user);
		user.setSentApplications(sentApplications);
		user.setReceivedApplications(receivedApplications);
		return user;

	}

}
