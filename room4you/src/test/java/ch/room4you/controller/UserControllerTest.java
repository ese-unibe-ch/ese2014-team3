package ch.room4you.controller;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ch.room4you.entity.Alert;
import ch.room4you.repository.AlertRepository;
import ch.room4you.service.AlertService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class UserControllerTest {
	 
	   @Mock
	    private Alert alert;
	   
	   @Mock
	    private AlertRepository alertRepository;
	   
	   @Mock
	    private AlertService alertService;

	    @InjectMocks
	    private AlertController alertController;

	    private MockMvc mockMvc;
	    

	    @Before
	    public void setup() {
	        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	        
	    }

	    
	    @Test
	    public void testGetAccount() throws Exception {
	        this.mockMvc.perform(get("/user-account.jsp"))
         .andExpect(status().is4xxClientError());
	        
	    }
	    


}
