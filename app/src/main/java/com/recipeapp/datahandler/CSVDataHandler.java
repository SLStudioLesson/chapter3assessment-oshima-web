package com.recipeapp.datahandler;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVDataHandler implements DataHandler {

    private String filePath;
    
    public CSVDataHandler() {
        this.filePath = "src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue;

                String name = parts[0];
                String[] ingredientNames = parts[1].split(",");
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (String ingredientName : ingredientNames) {
                    ingredients.add(new Ingredient(ingredientName.trim()));
                }
                recipes.add(new Recipe(name, ingredients));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return recipes;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException {
        // 未実装
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        // 未実装
        return null;
    }
}