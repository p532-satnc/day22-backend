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
        MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
        allMenus.add(new DinerMenu("DINER MENU", "Lunch"));
        allMenus.add(new PancakeHouseMenu("PANCAKE HOUSE MENU", "Breakfast"));
        allMenus.add(new CafeMenu("CAFE MENU", "Dinner"));

        Map<String, List<MenuItemRecord>> map = new HashMap<>();
        CompositeIterator iterator = new CompositeIterator();

        while (iterator.hasNext()) {
            MenuComponent component = (MenuComponent) iterator.next();
            if (component instanceof MenuItem item) {
                String parentMenu = component.getParentName();
                map.computeIfAbsent(parentMenu, k -> new ArrayList<>())
                        .add(new MenuItemRecord(item.getName(), item.getDescription(), item.isVegetarian(), item.getPrice()));
            }
        }
        return map;
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
