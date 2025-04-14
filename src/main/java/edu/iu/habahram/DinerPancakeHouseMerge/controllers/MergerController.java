package edu.iu.habahram.DinerPancakeHouseMerge.controllers;
import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItem;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.CafeRepository;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.DinerRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.PancakeHouseRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin
@RequestMapping("/merger")

public class MergerController {
    private final DinerRepository dinerRepository;
    private final PancakeHouseRepository pancakeHouseRepository;
    private final CafeRepository cafeRepository;

    public MergerController(DinerRepository dinerRepository, PancakeHouseRepository pancakeHouseRepository, CafeRepository cafeRepository) {
        this.dinerRepository = dinerRepository;
        this.pancakeHouseRepository = pancakeHouseRepository;
        this.cafeRepository = cafeRepository;
    }

    @GetMapping
    public List<MenuItem> getMergedMenu() {
        List<MenuItem> dinerMenu = Arrays.asList(dinerRepository.getTheMenu());
        List<MenuItem> pancakeMenu = pancakeHouseRepository.getTheMenu();
        List<MenuItem> cafeMenu = (List<MenuItem>) cafeRepository.getTheMenu();

        // ✅ Merge all menus in order, no sorting
        List<MenuItem> mergedMenu = new ArrayList<>();
        mergedMenu.addAll(dinerMenu);
        mergedMenu.addAll(pancakeMenu);
        mergedMenu.addAll(cafeMenu);

        return mergedMenu;
    }
}
