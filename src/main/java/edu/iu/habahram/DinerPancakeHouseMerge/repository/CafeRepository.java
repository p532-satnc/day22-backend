package edu.iu.habahram.DinerPancakeHouseMerge.repository;

import edu.iu.habahram.DinerPancakeHouseMerge.model.CafeMenu;
import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItem;

import java.util.HashMap;

public class CafeRepository {
    public HashMap<String, MenuItem> getTheMenu() {
        CafeMenu cafeMenu = new CafeMenu();
        return (HashMap<String, MenuItem>) cafeMenu.getItems();
    }
}
