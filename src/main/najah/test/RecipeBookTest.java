package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;

//RecipeBook class test
//Added parameterized Test (Not very easy to that here so I used if statement to make it work)
//Added am intentially failing test
//added timeout test
//added parallel excution here

@Execution(value = ExecutionMode.CONCURRENT)
@DisplayName("RecipeBook Tests")
class RecipeBookTest {

	RecipeBook recipebook;
	Recipe recipe1;
	Recipe recipe2;
	


    
    
    @BeforeEach
    void preTestMethod() {
    	recipebook=new RecipeBook();//Setting up the RecipeBook object
    	recipe1=new Recipe();
    	recipe1.setName("Dark Coffee");
    	recipe2=new Recipe();
    	recipe2.setName("Espresso");
    }

    
    @Test
    @DisplayName("addRecipe Test")
    void addRecipeTest1() {
    	assertTrue(recipebook.addRecipe(recipe1),"Your Recipe Already Exists");//Added the recipe
    	/*For the same recipe book I added the recipe again so that  exists =true is tested and checked*/
    }
    
    @Test
    @DisplayName("addRecipe Test (With Duplicate Recipe)")
    void addRecipeTest2() {
    	recipebook.addRecipe(recipe1);//Added recipe here
    	assertFalse(recipebook.addRecipe(recipe1), "Your Recipe was Added");//Added the same recipe again for full method coverage
    }
    
    @Test
    @DisplayName("getRecipes Test")
    void getRecipesTest1() {
    	Recipe[] recipeArray= new Recipe[4];
    	recipebook.addRecipe(recipe1);
    	recipebook.addRecipe(recipe2);
    	recipeArray[0]=recipe1;
    	recipeArray[1]=recipe2;
    	recipeArray[2]=null;
    	recipeArray[3]=null;

    	assertArrayEquals(recipeArray, recipebook.getRecipes());

    }
    
    @ParameterizedTest
    @DisplayName("deleteRecipe Test (Recipe to Delete Exists)")
    @ValueSource(ints={0,1})
    void deleteRecipeTest1(int indextoDelete) {
    	recipebook.addRecipe(recipe1);
    	recipebook.addRecipe(recipe2);
    	if(indextoDelete==0)
    	assertEquals(recipe1.getName(),recipebook.deleteRecipe(indextoDelete));
    	else
        assertEquals(recipe2.getName(),recipebook.deleteRecipe(indextoDelete));

    }
    
    @Test
    @DisplayName("deleteRecipe Test (Recipe to Delete doesn't Exist)")
    @Disabled
    void deleteRecipeTest2() {
    	recipebook.addRecipe(recipe1);
    	assertEquals("Coffee",recipebook.deleteRecipe(1));
    
    }//Must replace Coffee with null because the index 1 does not have any recipes
    
    @Test
    @DisplayName("editRecipe Test (Recipe to Edit Exists)")
    void editRecipeTest1() {
    	recipebook.addRecipe(recipe1);
    	assertEquals(recipe1.getName(), recipebook.editRecipe(0, recipe2));

    }
    
    @Test
    @DisplayName("editRecipe Test (Recipe to Edit doesn't Exist)")
    @Timeout(value = 1,unit = TimeUnit.SECONDS)
    void editRecipeTest2() {
    	recipebook.addRecipe(recipe1);
    	assertEquals(null, recipebook.editRecipe(1, recipe2));

    }
    
    
    //This was used internally, but just in case I made a test for it by itself
    @Test
    @DisplayName("Constructor Test")
    void constructorTest() {
    	Recipe[] recipeArray= new Recipe[4];
    	recipeArray[0]=null;
    	recipeArray[1]=null;
    	recipeArray[2]=null;
    	recipeArray[3]=null;
    	
    	assertArrayEquals(recipeArray, recipebook.getRecipes());


    }
    

}


