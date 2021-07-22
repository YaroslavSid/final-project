package com.alevel.dao;

import com.alevel.model.Drugs;
import com.alevel.model.Manufacturer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
public class DrugsDAOTest {

    @Autowired
    DrugsDAO drugsDAO;

    @Autowired
    ManufacturerDAO manufacturerDAO;

    Drugs drugs;
    Manufacturer manufacturer;

    @BeforeEach
    public void init() throws ParseException {
        manufacturer = new Manufacturer();
        manufacturer.setName("TestManufacturerName");
        manufacturerDAO.save(manufacturer);

        drugs = new Drugs();
        drugs.setName("TestName");
        drugs.setDateOfApproval(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-12"));
        drugs.setDescription("TestDescription");
        drugs.setManufacturer(manufacturer);
        drugs.setTreatmentFor("TestTreatmentFor");
        drugsDAO.save(drugs);
    }

    @AfterEach
    public void destroy () {
        Manufacturer manufacturer = manufacturerDAO.findManufacturerByName("TestManufacturerName");
        Drugs drugs = drugsDAO.findByName("TestName");

        if (manufacturer != null) {
            manufacturerDAO.delete(manufacturer);
        }
        if (drugs != null) {
            drugsDAO.delete(drugs);
        }
    }


    @Test
    public void shouldAddDrug () throws ParseException {

        Manufacturer manufacturerTest = new Manufacturer();
        Drugs drugsTest = new Drugs();

        manufacturerTest.setName("TestManufacturerName1");

        drugsTest.setName("TestName1");
        drugsTest.setDateOfApproval(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-12"));
        drugsTest.setDescription("TestDescription1");
        drugsTest.setManufacturer(manufacturerTest);
        drugsTest.setTreatmentFor("TestTreatmentFor");

        manufacturerDAO.save(manufacturerTest);
        Drugs drugsAdd = drugsDAO.save(drugsTest);
        Drugs drugsFind = drugsDAO.findByName("TestName1");

        assertThat(drugsAdd.getName(), is(drugsFind.getName()));
    }

    @Test
    public void shouldDeleteDrug () {

        drugsDAO.delete(drugs);
        Optional<Drugs> byId = drugsDAO.findById(drugs.getId());

        assertThat(byId.isEmpty(), is(true));
    }

    @Test
    public void shouldFindDrugById () {

        Optional<Drugs> byId = drugsDAO.findById(drugs.getId());
        Drugs drug = new Drugs();

        if (byId.isPresent()){
            drug = byId.get();
        }

        assertThat(drug, is(drugs));
    }

    @Test
    public void shouldUpdateDrug () throws ParseException {
        Manufacturer manufacturerTest = new Manufacturer();
        Drugs drug = new Drugs();

        manufacturerTest.setName("TestManufacturerName1");

        drug.setName("TestName1");
        drug.setDateOfApproval(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-12"));
        drug.setDescription("TestDescription1");
        drug.setManufacturer(manufacturerTest);
        drug.setTreatmentFor("TestTreatmentFor");

        manufacturerDAO.save(manufacturerTest);
        Drugs drugsAdd = drugsDAO.save(drug);
        drugsAdd.setName("TestName1Update");
        Drugs drugsUpdate = drugsDAO.save(drug);
        Drugs drugsFind = drugsDAO.findByName("TestName1Update");

        assertThat(drugsFind.getName(), is(drugsUpdate.getName()));

    }

    @Test
    public void shouldFindDrugByName () {

        Drugs drugsFind = drugsDAO.findByName("TestName");

        assertThat(drugsFind.getName(), is(drugs.getName()));

    }

}