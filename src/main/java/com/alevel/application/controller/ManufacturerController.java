package com.alevel.application.controller;


import com.alevel.model.Drugs;
import com.alevel.model.Manufacturer;
import com.alevel.service.DrugsService;
import com.alevel.service.ManufacturerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manufacturer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManufacturerController {

    DrugsService drugsService;
    ManufacturerService manufacturerService;

    @GetMapping("/new")
    public String createManufacturer(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturer/manufacturer-new";
    }

    @PostMapping("/new-manufacturer")
    public String addManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
        manufacturerService.createManufacturer(manufacturer);
        return "redirect:/drugs";
    }

    @GetMapping("/list-manufacturers")
    public String getListManufacturers(Model model) {
        List<Manufacturer> manufacturerList = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturerList);
        return "manufacturer/all-manufacturers";
    }

    @PostMapping("/{name}")
    public String deleteManufacturer(@PathVariable String name) {
        Drugs drug = drugsService.getByManufacturer(name);
        Integer id = manufacturerService.getManufacturer(name).getId();
        if (drug != null) {
            return "/manufacturer/delete-drugs";
        } else {
            manufacturerService.deleteManufacturer(id);
            return "redirect:/manufacturer/list-manufacturers";
        }
    }
}
