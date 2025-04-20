package edu.iu.habahram.DinerPancakeHouseMerge.model;

public class MenuItem extends MenuComponent{
    String name;
    String description;
    boolean vegetarian;
    double price;
    String parentMenuName;

    public MenuItem(String name,
                    String description,
                    boolean vegetarian,
                    double price,
                    String parentMenuName)
    {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
        this.parentMenuName = parentMenuName;
    }

    public String getParentMenuName() {
        return parentMenuName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }
    public String toString() {
        return (name + ", $" + price + "\n   " + description);
    }
    public MenuItem[] getItems() {
        MenuItem[] items = new MenuItem[1];
        items[0] = this;
        return items;
    }
}
