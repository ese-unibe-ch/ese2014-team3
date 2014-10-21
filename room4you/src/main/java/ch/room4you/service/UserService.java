package ch.room4you.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Image;
import ch.room4you.entity.Role;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.RoleRepository;
import ch.room4you.repository.UserRepository;
import ch.room4you.repository.ImageRepository;

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
	private ImageRepository imageRepository;


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
//		for (Ad ad : ads) {
//			List<Image> images = imageRepository.findByAd(ad);
//			ad.setImages(images);
//		}
		user.setAds(ads);
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
	

}
