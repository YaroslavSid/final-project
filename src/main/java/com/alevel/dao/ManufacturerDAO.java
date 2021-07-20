package com.alevel.dao;

import com.alevel.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerDAO extends JpaRepository<Manufacturer, Integer> {

    Manufacturer findManufacturerByName (String nameManufacturer);

}
