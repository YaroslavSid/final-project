package com.alevel.service.serviceImpl;

import com.alevel.dao.ManufacturerDAO;
import com.alevel.model.Manufacturer;
import com.alevel.service.ManufacturerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerDAO manufacturerDAO;

    @Transactional
    @Override
    public void createManufacturer(Manufacturer manufacturer) {
        manufacturerDAO.save(manufacturer);
    }

    @Transactional
    @Override
    public List<Manufacturer> findAll() {
        return manufacturerDAO.findAll();
    }

    @Override
    public void deleteManufacturer(Integer id) {
        manufacturerDAO.deleteById(id);
    }

    @Override
    public Manufacturer getManufacturer(String nameManufacturer) {
        return manufacturerDAO.findManufacturerByName(nameManufacturer);
    }


}
