package com.alevel.dao;


import com.alevel.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDetailsDAO extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByName(String name);

}
