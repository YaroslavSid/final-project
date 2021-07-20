package com.alevel.service;

import com.alevel.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    void createManufacturer (Manufacturer manufacturer);

    List<Manufacturer> findAll();

    void deleteManufacturer (Integer id);

    Manufacturer getManufacturer (String nameManufacturer);

}
