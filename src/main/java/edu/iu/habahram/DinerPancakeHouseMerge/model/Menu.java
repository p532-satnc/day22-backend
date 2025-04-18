package edu.iu.habahram.DinerPancakeHouseMerge.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Menu extends MenuComponent{
    ArrayList<MenuComponent> menuComponents = new ArrayList<MenuComponent>();
    String name;
    String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    public MenuComponent getChild(int i) {
        return (MenuComponent)menuComponents.get(i);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public MenuItem[] getItems() {
        ArrayList<MenuItem> items = new ArrayList<>();
        for (MenuComponent component : menuComponents) {
            if (component instanceof MenuItem) {
                items.add((MenuItem) component);
            } else if (component instanceof Menu subMenu) {
                // Recursively get items from the sub-menu
                items.addAll(Arrays.asList(subMenu.getItems()));
            }
        }
        return items.toArray(new MenuItem[0]);
    }

    public Iterator<MenuItem> createIterator() {
        return Arrays.asList(getItems()).iterator();
    }
}
