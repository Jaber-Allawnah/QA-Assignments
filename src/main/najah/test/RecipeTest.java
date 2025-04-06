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

import main.najah.code.Recipe;
import main.najah.code.RecipeException;

//Recipe class test
//Added lifecycle hooks
//Added parameterized Test for ingredient setters
//Added invalid inputs using exception checking
//Added timeout test
//Added intentionally failing test
//Tested constructor and equality
//Not required in the assignment but I did it extra

@DisplayName("Recipe Tests  (Extra for extra coverage)")
public class RecipeTest {
	
	Recipe r;
	
	@BeforeAll
	static void preTestClass() {
		System.out.println("Recipe Class Tests Are Starting");
	}
	
	@AfterAll
	static void postTestClass() {
		System.out.println("Recipe Class Tests Are Done");
	}
	
	@BeforeEach
	void preTestMethod() {
    	System.out.println("A Recipe test is about to run");

		r = new Recipe(); // Setting up the recipe object
	}
	
    @AfterEach
    void postTestMethod() {
    	System.out.println("A Recipe is Done");
    }
    
	@Test
	@DisplayName("Constructor Default Values Test")
	void testConstructor1() {
		assertEquals("", r.getName());
		assertEquals(0, r.getPrice());
		assertEquals(0, r.getAmtCoffee());
		assertEquals(0, r.getAmtMilk());
		assertEquals(0, r.getAmtSugar());
		assertEquals(0, r.getAmtChocolate());
	}

	@Test
	@DisplayName("setName Test with Valid Input")
	void setNameTest1() {
		r.setName("Nescafe");
		assertEquals("Nescafe", r.getName());
	}

	@Test
	@DisplayName("setName Test with inValid Input")
	void setNameTest2() {
		r.setName(null);
		assertEquals("", r.getName());
	}

	@ParameterizedTest
	@DisplayName("Ingredient Setters with Valid Inputs")
	@CsvSource({
		"1,2,3,4,5"
	})
	void ingridientsSettersTest1(String coffee, String milk, String sugar, String chocolate, String price) throws RecipeException {
		r.setAmtCoffee(coffee);
		r.setAmtMilk(milk);
		r.setAmtSugar(sugar);
		r.setAmtChocolate(chocolate);
		
		assertEquals(1, r.getAmtCoffee());
		assertEquals(2, r.getAmtMilk());
		assertEquals(3, r.getAmtSugar());
		assertEquals(4, r.getAmtChocolate());
	}

	@Test
	@DisplayName("Coffee Setter Test with inValid Input (negative)")
	void  ingridientsSettersTest2() {
		{
		    assertThrows(RecipeException.class, () -> r.setAmtCoffee("-1"));
		    assertThrows(RecipeException.class, () -> r.setAmtMilk("-2"));
		    assertThrows(RecipeException.class, () -> r.setAmtSugar("-3"));
		    assertThrows(RecipeException.class, () -> r.setAmtChocolate("-4"));
		}	}

	@Test
	@DisplayName("Ingredient Setter Test with inValid Input (letters)")
	void  ingridientsSettersTest3() {
	    assertThrows(RecipeException.class, ()->r.setAmtCoffee("abc"));
	    assertThrows(RecipeException.class, ()->r.setAmtMilk("milk"));
	    assertThrows(RecipeException.class, ()->r.setAmtSugar("sugar"));
	    assertThrows(RecipeException.class, ()->r.setAmtChocolate("choco"));
	}

	@Test
	@DisplayName("setPrice Test with Valid Input")
	void setPriceTest1() throws RecipeException {
		r.setPrice("10");
		assertEquals(10, r.getPrice());
	}
	
	@Test
	@DisplayName("setPrice Test with inValid Input (String)")
	void setPriceTest2() {
		assertThrows(RecipeException.class, ()->r.setPrice("twenty"));


	}

	@Test
	@DisplayName("setPrice Test with inValid Input (negative)")
	void setPriceTest3() {
		assertThrows(RecipeException.class, ()->r.setPrice("-10"));
	}

	@Test
	@DisplayName("Equality Test for Same Name")
	void equalsTest1() {
		r.setName("Latte");
		Recipe r2 = new Recipe();
		r2.setName("Latte");
		assertEquals(r, r2); // Recipes are equal by name
	}

	@Test
	@DisplayName("Equality Test with Different Names")
	void equalsTest2() {
		r.setName("Cappuccino");
		Recipe r2 = new Recipe();
		r2.setName("Mocha");
		assertNotEquals(r, r2);
	}

	@Test
	@DisplayName("toString Test")
	void toStringTest() {
		r.setName("Black Coffee");
		assertEquals("Black Coffee", r.toString());
	}

	@Test
	@DisplayName("Intentionally Failing Test (wrong sugar value)")
	@Disabled
	void setAmtSuagrTest() throws RecipeException {
		r.setAmtSugar("3");
		assertEquals(4, r.getAmtSugar()); // Should be 3 not 4
	}

	@Test
	@DisplayName("Timeout Test for hashCode Recipe without Name")
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	void hashCodeTes1() {
		assertEquals(31, r.hashCode());
	}


	@Test
	@DisplayName(" Test for hashCode with Recipe Name ")
	void hashCodeTest2() {
		r.setName("Nescafe");
		assertNotEquals(31, r.hashCode());
	}
	

	@Test
	@DisplayName("Equality Test with For same Object")
	void equalsTest3() {
		assertTrue(r.equals(r));
	}
	
	@Test
	@DisplayName("Equality Test with null Object")
	void equalsTest4() {
		assertFalse(r.equals(null));
	}
	
	

	
}

