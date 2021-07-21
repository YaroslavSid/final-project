package com.alevel.dao;

import com.alevel.model.Manufacturer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@DataJpaTest
class ManufacturerDAOTest {
    @Autowired
    ManufacturerDAO manufacturerDAO;

    Manufacturer manufacturerTest;


    @BeforeEach
    public void init() {
        manufacturerTest = new Manufacturer();
        manufacturerTest.setName("Rylaze");
        manufacturerTest = manufacturerDAO.save(manufacturerTest);
    }

    @AfterEach
    public void destroy() {
        Manufacturer manufacturer = manufacturerDAO.findManufacturerByName("Rylaze");

        if (manufacturer != null) {
            manufacturerDAO.delete(manufacturer);
        }
    }

    @Test
    public void shouldFindManufacturer() {

        Manufacturer manufacturer = manufacturerDAO.findManufacturerByName("Rylaze");
        assertThat(manufacturerTest, is(manufacturer));

    }

    @Test
    public void shouldAddManufacturer() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Verkazia");
        Manufacturer manufacturerAdd = manufacturerDAO.save(manufacturer);
        Manufacturer manufacturerFind = manufacturerDAO.findManufacturerByName("Verkazia");
        assertThat(manufacturerAdd.getName(), is(manufacturerFind.getName()));

    }

    @Test
    public void shouldDeleteManufacturer() {
        manufacturerDAO.delete(manufacturerTest);
        Optional<Manufacturer> byId = manufacturerDAO.findById(manufacturerTest.getId());
        assertThat(byId.isEmpty(), is(true));

    }

    @Test
    public void shouldUpdateManufacturer() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Kerendia");
        Manufacturer manufacturerAdd = manufacturerDAO.save(manufacturer);
        manufacturerAdd.setName("Kerendia1");
        Manufacturer manufacturerUpdate = manufacturerDAO.save(manufacturer);
        Manufacturer manufacturerFind  = manufacturerDAO.findManufacturerByName("Kerendia1");
        assertThat(manufacturerFind.getName(), is(manufacturerUpdate.getName()));

    }

}