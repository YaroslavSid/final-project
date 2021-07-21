package com.alevel.service.impl;

import com.alevel.dao.DrugsDAO;
import com.alevel.model.Drugs;
import com.alevel.service.DrugsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DrugsServiceImpl implements DrugsService {

    DrugsDAO drugsDAO;

    // todo
    @Transactional
    @Override
    public List<Drugs> findAll() {
        return drugsDAO.findAll();
    }

    @Transactional
    @Override
    public void createDrugs(Drugs drugs) {
        drugsDAO.save(drugs);
    }

    @Override
    public void deleteDrug(Integer id) {
        drugsDAO.deleteById(id);
    }

    @Transactional
    @Override
    public Drugs findDrugById(Integer id) {
        Optional<Drugs> optionalDrug = drugsDAO.findById(id);
        return optionalDrug.get();
    }

    @Transactional
    @Override
    public void updateDrug(Drugs drug) {
        drugsDAO.save(drug);
    }

    @Transactional
    @Override
    public Drugs findDrugByName(String drugName) {
        return drugsDAO.findByName(drugName);
    }

    @Transactional
    @Override
    public List<String> findAllDrugsByDateOfApproval(Date from, Date to) {
        List<Drugs> drugsList = drugsDAO.findDrugsByDateOfApprovalBetween(from, to);
        List<String> nameList = new ArrayList<>();
        for (Drugs d : drugsList) {
            nameList.add(d.getName());
        }
        return nameList;
    }

    @Transactional
    @Override
    public List<Drugs> getByManufacturer(String nameManufacturer) {
        return drugsDAO.findDrugsByManufacturerName(nameManufacturer);
    }

}
