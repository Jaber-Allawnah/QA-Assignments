package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import main.najah.code.Calculator;

//Calculator class test
//Added lifecycle hooks
//Added ordering in testing
//Added and intentioally failing test and disabled it
//Added parameterized Test

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Calculator Tests")
public class CalculatorTest {
	
    Calculator calc;
    @BeforeAll
    static void preTestClass() {
    	System.out.println("Calculator Class Tests Are Starting");
    }
    
    @AfterAll
    static void postTestClass() {
    	System.out.println("Calculator Class Tests Are Done");
    }
    
    @BeforeEach
    void preTestMethod() {
    	System.out.println("A Calculator test is about to run");
    	calc=new Calculator();//Setting up the Calculator object
    }
    
    @AfterEach
    void postTestMethod() {
    	System.out.println("A Calculator is Done");
    }
    
    @Test
    @Order(1)
    @DisplayName("Add Test with Posititve Numbers")
    @Disabled
    void addTest1() {
    	assertEquals(12, calc.add(2,3,4,5));
    }//This test method is disabled and it will give an error
    //To solve the error, instead of 12 in the left parameter of assertEquals it must be 14
    //assertEquals(14, calc.add(2,3,4,5));

    
    @Test
    @Order(2)
    @DisplayName("Add Test with Negative Numbers")
    void addTest2() {
    	assertEquals(-14, calc.add(-2,-3,-4,-5));
    }
    
    @ParameterizedTest
    @Order(3)
    @DisplayName("Division with Valid Input")
    @CsvSource({
    	"2,4,2",
    	"5,10,2"
    	})
    void divideTest1(int value,int num1,int num2) {
    	assertEquals(value, calc.divide(num1,num2));
    }
    
    @Test
    @Order(4)
    @DisplayName("Division with invalid Input")
    void divideTest2() {
    	assertThrows(ArithmeticException.class, ()->calc.divide(4,0));
    }
    
    @Test
    @Order(5)
    @DisplayName("Factorial with Valid Input")
    @Timeout(value = 500, unit=TimeUnit.MILLISECONDS)
    void factorialTest1() {
    	assertEquals(24, calc.factorial(4));
    }
    
    @Test
    @Order(5)
    @DisplayName("Factorial with invalid Input")
    @Timeout(value = 500, unit=TimeUnit.MILLISECONDS)
    void factorialTest2() {
    	assertThrows(IllegalArgumentException.class, ()->calc.factorial(-4));
    }


	

}
