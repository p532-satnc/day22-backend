package edu.iu.habahram.DinerPancakeHouseMerge.controllers;

import java.util.Iterator;
import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItem;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.DinerRepository;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.PancakeHouseRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class PancakeHouseIterator implements Iterator {
    List<MenuItem> items;
    int position = 0;

    public PancakeHouseIterator(List<MenuItem> items) {
        this.items = items;
    }

    public MenuItem next(){
        MenuItem menuItem = items.get(position);
        position=position+1;
        return menuItem;
    }

    public boolean hasNext(){
        return position < items.toArray().length && position >= 0;
    }
}
