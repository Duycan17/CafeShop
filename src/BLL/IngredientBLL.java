/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

/**
 *
 * @author duy
 */
import DAL.Ingredient;
import DAL.IngredientDAL;
import java.util.List;

public class IngredientBLL {
    private IngredientDAL ingredientDAL;

    public IngredientBLL() {
        ingredientDAL = new IngredientDAL();
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientDAL.getAllIngredients();
    }

    public Ingredient getIngredientById(int ingredientId) {
        return ingredientDAL.getIngredientById(ingredientId);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientDAL.addIngredient(ingredient);
    }

    public void updateIngredient(Ingredient ingredient) {
        ingredientDAL.updateIngredient(ingredient);
    }

    public void deleteIngredient(int ingredientId) {
        ingredientDAL.deleteIngredient(ingredientId);
    }
    public List<Ingredient> getIngredientByName(String name){
        return ingredientDAL.getIngredientsByName(name);
    }
}