/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author duy
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAL {

    private Connection connection;

    public IngredientDAL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafeShop?characterEncoding=UTF-8";
            String username = "root";
            String password = "123";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredient = new ArrayList<>();
        try {
            String query = "SELECT * FROM ingredient";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String unit = resultSet.getString("unit");
                double quantity = resultSet.getDouble("quantity");
                ingredient.add(new Ingredient(id, name, unit, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredient;
    }

    public Ingredient getIngredientById(int ingredientId) {
        try {
            String query = "SELECT * FROM ingredient WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ingredientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String unit = resultSet.getString("unit");
                double quantity = resultSet.getDouble("quantity");
                return new Ingredient(ingredientId, name, unit, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addIngredient(Ingredient ingredient) {
        try {
            String query = "INSERT INTO ingredient (name, unit, quantity) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ingredient.getName());
            statement.setString(2, ingredient.getUnit());
            statement.setDouble(3, ingredient.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateIngredient(Ingredient ingredient) {
        try {
            String query = "UPDATE ingredient SET name = ?, unit = ?, quantity = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ingredient.getName());
            statement.setString(2, ingredient.getUnit());
            statement.setDouble(3, ingredient.getQuantity());
            statement.setInt(4, ingredient.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteIngredient(int ingredientId) {
        try {
            String query = "DELETE FROM ingredient WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ingredientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ingredient> getIngredientsByName(String name) {
        List<Ingredient> ingredientt = new ArrayList<>();
        try {
            String query = "SELECT * FROM ingredient WHERE name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String ingredientName = resultSet.getString("name");
                String unit = resultSet.getString("unit");
                double quantity = resultSet.getDouble("quantity");
                Ingredient ingredient = new Ingredient(0,ingredientName, unit, quantity);
                ingredientt.add(ingredient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientt;
    }
}
