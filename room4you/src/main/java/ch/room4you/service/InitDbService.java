package ch.room4you.service;

/**
 * Initializes the database after startup with admin-user, user1 and ad1
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ch.room4you.entity.Ad;
import ch.room4you.entity.Image;
import ch.room4you.entity.Role;
import ch.room4you.entity.RoomMate;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.ImageRepository;
import ch.room4you.repository.RoleRepository;
import ch.room4you.repository.RoomMateRepository;
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
	
	@Autowired
	private RoomMateRepository roomMateRepository;
	
		
	@PostConstruct
	public void init() throws ParseException, IOException {
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
	         ad1.setStreet("Fabrikstrasse 8");
	         ad1.setCity("Bern");
	         ad1.setZip(3007);
	         Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2014-11-01");
	         ad1.setAvailableFrom(date);
	         ad1.setNbrRooms((float) 4.5);
	         ad1.setRentPerMonth("CHF 1'200.-");
	         ad1.setAdditionalInformation("Some additional Info");
	         adRepository.save(ad1);
	         
	         RoomMate roomMate = new RoomMate();
	         roomMate.setUser(userAdmin);
	         roomMate.setAd(ad1);
	         roomMateRepository.save(roomMate);
	         
	         RoomMate roomMate2 = new RoomMate();
	         roomMate2.setUser(user1);
	         roomMate2.setAd(ad1);
	         roomMateRepository.save(roomMate2);
	         
	         	         
	         	         
	         Image image1 = new Image();  
	         byte[] image = "Any String you want".getBytes();
	         image1.setImage(image);
	         image1.setAd(ad1);
	         imageRepository.save(image1);       

			 
		}

	}
	
	public MultipartFile convert(File file) throws IOException
	{    
		   	File newFile = file;
		    DiskFileItem fileItem = new DiskFileItem("file", "text/plain", false, newFile.getName(), (int) newFile.length() , newFile.getParentFile());
		    fileItem.getOutputStream();
		    MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		    return multipartFile;
	}

	private void encryptPassword(User user, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(password));
	}
}
