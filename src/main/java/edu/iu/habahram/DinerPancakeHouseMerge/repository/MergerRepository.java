package edu.iu.habahram.DinerPancakeHouseMerge.repository;

import edu.iu.habahram.DinerPancakeHouseMerge.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.CompositeIterator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Repository
public class MergerRepository {
    public List<MenuItemRecord> getTheMenuItems() {
        MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
        allMenus.add(new DinerMenu("DINER MENU", "Lunch"));
        allMenus.add(new PancakeHouseMenu("PANCAKE HOUSE MENU", "Breakfast"));
        allMenus.add(new CafeMenu("CAFE MENU", "Dinner"));
        MenuItem[] menuItems = allMenus.getItems();
        return Arrays.stream(menuItems)
                .map(x -> new MenuItemRecord(x.getName(),
                        x.getDescription(),
                        x.isVegetarian(),
                        x.getPrice())).toList();
    }

    public List<MenuItemRecord> getTheMenuItemsByMenuName(String menuName) {
        return getTheMenuItemsWithMenus().entrySet().stream()
                .filter(entry -> entry.getKey().equals(menuName))
                .flatMap(entry -> entry.getValue().stream())
                .toList();
    }

    public Map<String, List<MenuItemRecord>> getTheMenuItemsWithMenus() {
        Menu dinerMenu = new DinerMenu("DINER MENU", "Lunch");
        Menu pancakeMenu = new PancakeHouseMenu("PANCAKE HOUSE MENU", "Breakfast");
        Menu cafeMenu = new CafeMenu("CAFE MENU", "Dinner");

        Map<String, List<MenuItemRecord>> map = new HashMap<>();

        // Helper function to add items from a menu to the map
        addMenuItemsToMap(map, dinerMenu);
        addMenuItemsToMap(map, pancakeMenu);
        addMenuItemsToMap(map, cafeMenu);

        return map;
    }

    private void addMenuItemsToMap(Map<String, List<MenuItemRecord>> map, Menu menu) {
        String menuName = menu.getName();
        Iterator<MenuItem> iterator = menu.createIterator();
        List<MenuItemRecord> records = new ArrayList<>();

        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            records.add(new MenuItemRecord(item.getName(), item.getDescription(), item.isVegetarian(), item.getPrice()));
        }

        map.put(menuName, records);
    }

    public String saveCustomer(String username, String password, String email) {
        String line = username + "," + password + "," + email + "\n";
        try {
            Files.write(Paths.get("data/customers.txt"), line.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return "Signup successful!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to save customer.";
        }
    }
}