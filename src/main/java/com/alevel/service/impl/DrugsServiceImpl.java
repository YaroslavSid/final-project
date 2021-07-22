package com.alevel.service.impl;

import com.alevel.dao.DrugsDAO;
import com.alevel.model.Drugs;
import com.alevel.service.DrugsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DrugsServiceImpl implements DrugsService {

    DrugsDAO drugsDAO;


    @Override
    public List<Drugs> findAll() {
        log.debug("Getting all drugs");
        return drugsDAO.findAll();
    }

    @Transactional
    @Override
    public void createDrugs(Drugs drugs) {
        log.debug("Save drug");
        drugsDAO.save(drugs);
    }

    @Transactional
    @Override
    public void deleteDrug(Integer id) {
        log.debug("Deleting drug (id = {})", id);
        drugsDAO.deleteById(id);
    }


    @Override
    public Drugs findDrugById(Integer id) {
        log.debug("Find drug by (id = {}) ", id);
        Optional<Drugs> optionalDrug = drugsDAO.findById(id);
        if (optionalDrug.isPresent()) {
            return optionalDrug.get();
        } else {
            log.error("Cannot find drud by (id = {})", id);
            throw new RuntimeException("Drugs not found");
        }
    }

    @Transactional
    @Override
    public void updateDrug(Drugs drug) {
        log.debug("Updating drug = ", drug);
        drugsDAO.save(drug);
    }


    @Override
    public Drugs findDrugByName(String drugName) {
        log.debug("Find drug by name = ", drugName);
        return drugsDAO.findByName(drugName);
    }


    @Override
    public List<String> findAllDrugsByDateOfApproval(Date from, Date to) {
        log.debug("Find list drugs by select date");
        List<Drugs> drugsList = drugsDAO.findDrugsByDateOfApprovalBetween(from, to);
        List<String> nameList = new ArrayList<>();
        for (Drugs d : drugsList) {
            nameList.add(d.getName());
        }
        return nameList;
    }


    @Override
    public List<Drugs> getByManufacturer(String nameManufacturer) {
        log.debug("Getting drug by manufacturer (String name) = ", nameManufacturer);
        return drugsDAO.findDrugsByManufacturerName(nameManufacturer);
    }

}
