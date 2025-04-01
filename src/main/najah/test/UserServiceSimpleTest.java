package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.UserService;

class UserServiceSimpleTest {
	
	UserService service;
		
    @BeforeAll
    static void preTestClass() {
    	System.out.println("UserService Class Tests Are Starting");
    }
    
    @AfterAll
    static void postTestClass() {
    	System.out.println("UserService Class Tests Are Done");
    }
    
    @BeforeEach
    void preTestMethod() {
    	System.out.println("A UserService test is about to run");
    	service= new UserService();
    }
    
    @AfterEach
    void postTestMethod() {
    	System.out.println("A UserService is Done");
    }
    
    @Test
    @DisplayName("isValidEmail Test with Valid Input")
    void isValidEmail1() {
    	assertTrue(service.isValidEmail("jaber2003@gmail.com"),"Your Email is inValid");
    }
    
    @Test
    @DisplayName("isValidEmail Test with Invalid Input (No @)")
    void isValidEmail2() {
    	assertFalse(service.isValidEmail("jaber2003gmail.com"),"Your Email is Valid");
    }
    
    @Test
    @DisplayName("isValidEmail test with invalid input (NULL)")
    void isValidEmail3() {
    	assertFalse(service.isValidEmail(null),"Your Email is Valid");
    }
    
    @Test
    @DisplayName("isValidEmail test with invalid input (No .)")
    void isValidEmail4() {
    	assertFalse(service.isValidEmail("jaber2003@gmailcom"),"Your Email is Valid");
    }
    
    @Test
    @DisplayName("authenticate Test with Valid Input")
    @Disabled
    void authenticate1() {
    	assertFalse(service.authenticate("admin","1234"),"Your Username and Password are Incorrect");
    }//This is an intentionally failing test, it must use assertTrue instead of assertFalse
    
    @ParameterizedTest
    @DisplayName("authenticate Test with Invalid Inputs using parameterized test")
    @CsvSource({
    	"jaber,1234,false",
    	"admin,1234,true",
    	"admin,123,false",
    	"admn,1234,false",    	
    })
    @Timeout(value = 500,unit = TimeUnit.MILLISECONDS)
    void authenticate2(String name,String password, boolean state) {
    	assertEquals(state, service.authenticate(name, password));
    	
    }//Note: I added  "admin,1234,true" here to get full coverage, when authenticate1 is fixed no need for it here
    //I also made sure that I tested both when username is right and password is wring and the opposite

	

}
