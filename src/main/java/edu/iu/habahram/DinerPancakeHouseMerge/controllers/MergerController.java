package edu.iu.habahram.DinerPancakeHouseMerge.controllers;
import edu.iu.habahram.DinerPancakeHouseMerge.model.Menu;
import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItem;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.CafeRepository;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.DinerRepository;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.MergerRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.PancakeHouseRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin
@RequestMapping("/merger")

public class MergerController {
    private final MergerRepository mergerRepository;

    public MergerController(MergerRepository mergerRepository) {
        this.mergerRepository = mergerRepository;
    }

    @GetMapping
    public List<MenuItem> getMergedMenu() {
        List<MenuItem> mergedMenu = new ArrayList<>();

        ArrayList<Menu> menus = mergerRepository.getTheMenus();
        for (Menu menu : menus) {
            Iterator<MenuItem> iterator = menu.createIterator();
            while (iterator.hasNext()) {
                mergedMenu.add(iterator.next());
            }
        }

        return mergedMenu;
    }
}
