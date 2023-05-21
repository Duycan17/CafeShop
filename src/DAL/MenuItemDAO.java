package DAL;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author duy
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAL (Data Access Layer)
public class MenuItemDAO {

    private Connection conn;

    public MenuItemDAO() {
        try {
            // Initialize connection to MySQL database
            String url = "jdbc:mysql://localhost:3306/cafeShop";
            String user = "root";
            String password = "123";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        try {
            // Execute SQL query to retrieve all menu items
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM menu_items");

            // Map result set to list of MenuItem objects
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                MenuItem menuItem = new MenuItem(id, name, description, price);
                menuItems.add(menuItem);
            }

            // Close result set and statement
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItems;
    }

    public MenuItem getMenuItemById(int id) {
        MenuItem menuItem = null;

        try {
            // Execute SQL query to retrieve menu item by id
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM menu_items WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // Map result set to MenuItem object
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                menuItem = new MenuItem(id, name, description, price);
            }

            // Close result set and statement
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItem;
    }

    public boolean updateMenuItem(MenuItem menuItem) {
        boolean success = false;

        try {
            // Execute SQL query to update menu item by id
            PreparedStatement stmt = conn.prepareStatement("UPDATE menu_items SET name = ?, description = ?, price = ? WHERE id = ?");
            stmt.setString(1, menuItem.getName());
            stmt.setString(2, menuItem.getDescription());
            stmt.setDouble(3, menuItem.getPrice());
            stmt.setInt(4, menuItem.getId());
            int rowsAffected = stmt.executeUpdate();

            // Check if update was successful
            if (rowsAffected > 0) {
                success = true;
            }

            // Close statement
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public void saveMenuItem(MenuItem menuItem) {
        try {
            // Execute SQL query to save/update menu item
            PreparedStatement stmt;
            if (menuItem.getId() == 0) {
                // Insert new menu item
                stmt = conn.prepareStatement("INSERT INTO menu_items (name, description, price) VALUES (?, ?, ?)");
                stmt.setString(1, menuItem.getName());
                stmt.setString(2, menuItem.getDescription());
                stmt.setDouble(3, menuItem.getPrice());
            } else {
                // Update existing menu item
                stmt = conn.prepareStatement("UPDATE menu_items SET name = ?, description = ?, price = ? WHERE id = ?");
                stmt.setString(1, menuItem.getName());
                stmt.setString(2, menuItem.getDescription());
                stmt.setDouble(3, menuItem.getPrice());
                stmt.setInt(4, menuItem.getId());
            }

            stmt.executeUpdate();

            // Close statement
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenuItem(int id) {
        try {
            // Execute SQL query to delete menu item by id
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM menu_items WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

            // Close statement
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
