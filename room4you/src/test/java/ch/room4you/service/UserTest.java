package ch.room4you.service;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ch.room4you.entity.Role;
import ch.room4you.entity.User;
import ch.room4you.repository.RoleRepository;
import ch.room4you.repository.UserRepository;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/springData.xml","file:src/main/webapp/WEB-INF/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserTest {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;

    
    @Test
    public void testUserCreation(){
    		String userName = "test";
    		String userEmail = "ese2014team3@gmail.com";
    		String userPassword = "test";
    		String userAboutMe = "I am the admin of room4you and I am 12 years old";
    		
    		Role roleUser = new Role();
    		roleUser.setName("ROLE_USER");
    		roleRepository.save(roleUser);
    		
    	
    		User testUser = new User();   		

    		
    		testUser.setEnabled(true);
    		testUser.setName(userName);
    		testUser.setEmail(userEmail);
    		testUser.setPassword(userPassword);
    		List<Role> roles = new ArrayList<Role>();
    		roles.add(roleUser);
    		testUser.setRoles(roles);
    		testUser.setAboutMe(userAboutMe);
    		userRepository.save(testUser);
    		
    		assertEquals(testUser.getName(), userName);
    		assertEquals(testUser.getEmail(), userEmail);
    		assertEquals(testUser.getPassword(), userPassword);
    		assertTrue(testUser.getRoles().contains(roleUser));
    		assertEquals(testUser.getAboutMe(), userAboutMe);
    }
    
    
    @Test
    public void testFindUserByName() {
    	String NAME =  "test";    	
    	User user = new User();
    	user.setName(NAME);
        user =  userRepository.save(user);        
        User findUser = userRepository.findByName(NAME);
        assertEquals(findUser.getName(), NAME);
    }

}