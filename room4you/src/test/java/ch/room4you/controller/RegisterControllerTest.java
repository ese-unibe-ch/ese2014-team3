package ch.room4you.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import ch.room4you.entity.User;
import ch.room4you.service.UserService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/defs/general.xml", "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml","file:src/main/webapp/WEB-INF/springData.xml","file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class RegisterControllerTest {

    @Autowired
    private WebApplicationContext wac;
    
    
    private MockMvc mockMvc;
    
	   @Autowired
	   private UserService userService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

    }

    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(get("/register.html"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/layout/classic.jsp"));


    }
    
    @Test
    public void testUserAvailable() throws Exception{
//    	String username = "Test";
//    	Boolean available = userService.findOneByName(username) == null;    	
//    	
//    	 this.mockMvc.perform(get("/available").param("username", username))
//         .andExpect(status().isOk())
//         .andExpect(model().attributeExists("username"))
//    	 .andExpect(content().string(available.toString())); 	
    	 
    	 
    }
    
    

}


