package com.alevel.dao;

import com.alevel.model.Drugs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DrugsDAO extends JpaRepository<Drugs, Integer> {

    // todo
    @Query("SELECT d FROM Drugs d WHERE d.name= ?1")
    Drugs findByName(String drugName);


    @Query("SELECT d from Drugs d where d.dateOfApproval BETWEEN :from AND :to")
    List<Drugs> findDrugsByDateOfApprovalBetween(@Param("from") Date from, @Param("to") Date to);

    Drugs findDrugsByManufacturerName(String nameManufacturer);

}