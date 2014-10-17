package ch.room4you.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Image;
import ch.room4you.entity.Role;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.ImageRepository;
import ch.room4you.repository.RoleRepository;
import ch.room4you.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdRepository adRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@PostConstruct
	public void init() {
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			Role roleUser = new Role();
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);

			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);

			User userAdmin = new User();
			userAdmin.setEnabled(true);
			userAdmin.setName("admin");
			encryptPassword(userAdmin, "admin");
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleAdmin);
			roles.add(roleUser);
			userAdmin.setRoles(roles);
			userRepository.save(userAdmin);
			
			User user1 = new User();
			user1.setEnabled(true);
			user1.setName("user1");
			user1.setEmail("a@b.ch");
			encryptPassword(user1, "user1");
			List<Role> rolesUser = new ArrayList<Role>();
			rolesUser.add(roleUser);
			user1.setRoles(rolesUser);
			userRepository.save(user1);
		
			 
			 Ad ad1 = new Ad();
			 ad1.setTitle("Wohnung1");
			 ad1.setPublishedDate(new Date());
			 ad1.setDescription("Wunderschöne neue Wohnung");
			   //save image into database
	         ad1.setUser(userAdmin);
	         adRepository.save(ad1);
	         
	         Image image1 = new Image();  
//	         image1.setImageFromPath("/Users/Sven/Qsync/Studium/Informatik/ESE/ESEGit/ese2014-team3/ese2014-team3/room4you/images/wohnung1.JPG");
	         image1.setImage("Soon here will be an image");
	         image1.setAd(ad1);
//	         imageRepository.save(image1);
			 
		}

	}

	private void encryptPassword(User user, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(password));
	}
}
