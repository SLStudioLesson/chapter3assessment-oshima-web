package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Recipe;
import com.recipeapp.model.Ingredient;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    private void displayRecipes() {
        try {
            ArrayList<Recipe> recipes = dataHandler.readData();
            if(recipes.isEmpty()) {
                System.out.println("No recipes available.");
            } else {
                System.out.println("Recipes:");
                for (Recipe recipe : recipes) {
                    System.out.println("-----------------------------------");
                    System.out.println("Recipe Name: " + recipe.getName());
                    System.out.println("Main Ingredients: ");
                    for (int i = 0; i <recipe.getIngredients().size(); i++) {
                        System.out.print(recipe.getIngredients().get(i).getName());
                        if (i < recipe.getIngredients().size() - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println();
                }
                System.out.println("-----------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void addNewRecipe() {
        try {
            System.out.println();
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Enter ingredients (type 'done' when finished):");
            while (true) {
                System.out.print("Ingredient: ");
                String ingredient = reader.readLine();
                if ("done".equalsIgnoreCase(ingredient)) {
                    break;
                }
                ingredients.add(new Ingredient(ingredient));
            }

            Recipe recipe = new Recipe(recipeName, ingredients);
            dataHandler.writeData(recipe);

            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
    
    public void displayMenu() {

        

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                    displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }
}
