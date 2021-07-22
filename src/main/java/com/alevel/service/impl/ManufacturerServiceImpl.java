package com.alevel.service.impl;

import com.alevel.dao.ManufacturerDAO;
import com.alevel.model.Manufacturer;
import com.alevel.service.ManufacturerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerDAO manufacturerDAO;


    @Transactional
    @Override
    public void createManufacturer(Manufacturer manufacturer) {
        log.debug("Save drug");
        manufacturerDAO.save(manufacturer);
    }


    @Override
    public List<Manufacturer> findAll() {
        log.debug("Getting all drugs");
        return manufacturerDAO.findAll();
    }

    @Transactional
    @Override
    public void deleteManufacturer(Integer id) {
        log.debug("Deleting drug by (id = {}) ", id);
        manufacturerDAO.deleteById(id);
    }

    @Override
    public Manufacturer getManufacturer(String nameManufacturer) {
        log.debug("Getting manufacturer  by name (String name) = ", nameManufacturer);
        return manufacturerDAO.findManufacturerByName(nameManufacturer);
    }


}
