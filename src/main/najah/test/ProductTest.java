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
import main.najah.code.Product;

//Product class test
//Added lifecycle hooks
//Added and intentioally failing test and disabled it
//Added parameterized Test


@DisplayName("Product Tests")
public class ProductTest {
    Product p;

	
    @BeforeAll
     static void preTestClass() {
    	System.out.println("Product Class Tests Are Starting");
    }
    
    @AfterAll
    static void postTestClass() {
   	System.out.println("Product Class Tests Are Done");
   }
    
    @BeforeEach
    void preTestMethod() {
    	System.out.println("A Product test is about to run");
    	p=new Product("Keyboard", 60.00);//Setting up the product object
    }
    
    @AfterEach
    void postTestMethod() {
    	System.out.println("A Product is Done");
    }
    
    @Test
    @DisplayName("Constructor with Valid Inputs")
    void testConstructor1() {
    	assertEquals("Keyboard", p.getName());
    	assertEquals(60.00, p.getPrice());
    }
    
    @Test
    @DisplayName("Constructor with inValid Inputs")
    void testConstructor2() {
    	assertThrows(IllegalArgumentException.class, ()->new Product("Mouse",-100));
    }
    
    @ParameterizedTest
    @DisplayName("applyDiscount Test with Valid Inputs")
    @CsvSource({
        "30.0,42.0",
        "0.0,60.0",
        "50.0,30.0"
    })
    void applyDiscountTest1(double discount, double newPrice) {
    	p.applyDiscount(discount);
    	assertEquals(discount, p.getDiscount());//Checking for the discount if it was applied
    	assertEquals(newPrice, p.getFinalPrice());//Checking for the price if it was updated
    }//Note p.getDiscount() may not be needed to the test because p.getFinalPrice() will ensure that, but I added it for 100% coverage
    //ParameterizedTest with multiple prices and discounts
    
    @Test
    @DisplayName("applyDiscount Test with Invalid Input >50 ")
    @Timeout(value =500,unit = TimeUnit.MILLISECONDS)
    void applyDiscountTest2() {

    	assertThrows(IllegalArgumentException.class, ()->p.applyDiscount(60.0));
    	
    }
    
    @Test
    @DisplayName("applyDiscount Test with Invalid Input <0 ")
    @Disabled
    void applyDiscountTest3() {
    	assertThrows(ArithmeticException.class, ()->p.applyDiscount(-20.0));
    }//This is an intentionally failing test, and the way to solve it is replacing the argument with: assertThrows(IllegalArgumentException.class, ()->p.applyDiscount(-20.0));

    
    //The following 3 tests were used internally in my other test cases but I added seperate test case for each one of them just in case
    //Because it wasn't mentioned wither to test all test classes or only focus of the test coverage
    @Test
    @DisplayName("getName Test")
    void getNameTest() {
    	assertEquals("Keyboard", p.getName());
    }
    
    @Test
    @DisplayName("getPrice Test")
    void getPriceTest() {
    	assertEquals(60, p.getPrice());
    }
    
    @Test
    @DisplayName("getDiscount Test")
    void getDiscountTest() {
    	assertEquals(0, p.getDiscount());
    }
    

}
