package com.alevel.service;

import com.alevel.model.Drugs;

import java.util.Date;
import java.util.List;


public interface DrugsService {

    List<Drugs> findAll();

    void createDrugs (Drugs drugs);

    void deleteDrug (Integer id);

    Drugs findDrugById (Integer id);

    void updateDrug (Drugs drug);

    Drugs findDrugByName (String drugName);

    List<String> findAllDrugsByDateOfApproval (Date from, Date to);

    Drugs getByManufacturer (String nameManufacturer);
}
