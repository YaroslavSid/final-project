package com.alevel.application.dao;

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
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping( value = "/drugs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DrugsController {

    DrugsService drugsService;
    ManufacturerService manufacturerService;

    @GetMapping
    public String findAll(Model model) {
        List<Drugs> drugs = drugsService.findAll();
        model.addAttribute("drugs", drugs);
        return "drugs/drugs-list";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("drug", new Drugs());
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "drugs/drug-new";
    }

    @PostMapping
    public String create(@ModelAttribute("drug") Drugs drugs, @RequestParam("date") String date) {
        Date dateOfApproved = null;
        try {
            dateOfApproved = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        drugs.setDateOfApproval(dateOfApproved);
        drugsService.createDrugs(drugs);
        return "redirect:/drugs";
    }

    @PostMapping("/{id}")
    public String deleteDrug (@PathVariable Integer id) {
        drugsService.deleteDrug(id);
        return "redirect:/drugs";
    }

    @GetMapping("edit/{id}")
    public String editDrug (@PathVariable Integer id, Model model) {
        model.addAttribute("drug", drugsService.findDrugById(id));
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "drugs/drugs-edit";
    }

    @PostMapping("/edit-drug")
    public String updateDrug (@ModelAttribute("drug") Drugs drugs, @RequestParam("date") String date) {
        Date dateOfApproved = null;
        try {
            dateOfApproved = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        drugs.setDateOfApproval(dateOfApproved);
        drugsService.updateDrug(drugs);
        return "redirect:/drugs";
    }
}
