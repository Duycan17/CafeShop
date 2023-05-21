/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.MenuItem;
import DAL.MenuItemDAO;
import java.util.List;

/**
 *
 * @author duy
 */
// BLL (Business Logic Layer)
public class MenuItemService {
    private MenuItemDAO menuItemDAO;

    public MenuItemService() {
        this.menuItemDAO = new MenuItemDAO();
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemDAO.getAllMenuItems();
    }
    
    public MenuItem getMenuItemById(int id) {
        return menuItemDAO.getMenuItemById(id);
    }
    
    public void saveMenuItem(MenuItem menuItem) {
        if (menuItem.getName().isEmpty()) {
            throw new IllegalArgumentException("Menu item name cannot be empty");
        }
        
        menuItemDAO.saveMenuItem(menuItem);
    }
    
    public void deleteMenuItem(int id) {
        menuItemDAO.deleteMenuItem(id);
    }
}