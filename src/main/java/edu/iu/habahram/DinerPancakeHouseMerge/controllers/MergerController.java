package edu.iu.habahram.DinerPancakeHouseMerge.controllers;
import edu.iu.habahram.DinerPancakeHouseMerge.model.Customer;
import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItemRecord;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.MergerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/merger")

public class MergerController {

    MergerRepository mergerRepository;

    public MergerController(MergerRepository mergerRepository) {
        this.mergerRepository = mergerRepository;
    }

    @GetMapping
    public List<MenuItemRecord> get() {
        return mergerRepository.getTheMenuItems();
    }

    @GetMapping("/vegetarian")
    public List<MenuItemRecord> getVegetarianItems() {
        return mergerRepository.getTheMenuItems().stream()
                .filter(MenuItemRecord::vegetarian)
                .toList();
    }

    @GetMapping("/breakfast")
    public List<MenuItemRecord> getBreakfastItems() {
        return mergerRepository.getTheMenuItemsByMenuName("PANCAKE HOUSE MENU");
    }

    @GetMapping("/lunch")
    public List<MenuItemRecord> getLunchItems() {
        return mergerRepository.getTheMenuItemsByMenuName("DINER MENU");
    }

    @GetMapping("/supper")
    public List<MenuItemRecord> getSupperItems() {
        return mergerRepository.getTheMenuItemsByMenuName("CAFE MENU");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Customer customer) {
        String result = mergerRepository.saveCustomer(customer.username(), customer.password(), customer.email());
        return ResponseEntity.ok(result);
    }
}
