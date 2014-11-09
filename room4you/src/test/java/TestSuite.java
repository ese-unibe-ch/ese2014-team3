

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.room4you.controller.AlertControllerTest;
import ch.room4you.controller.IndexControllerTest;
import ch.room4you.controller.UserControllerTest;
import ch.room4you.service.AdTest;
import ch.room4you.service.AlertTest;
import ch.room4you.service.UserTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
   AdTest.class,
   AlertTest.class,
   UserTest.class,
   
   AlertControllerTest.class,
   UserControllerTest.class,
   IndexControllerTest.class
})
public class TestSuite {   
} 