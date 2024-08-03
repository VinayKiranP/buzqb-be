package com.buz.buzqb.repository;

import com.buz.buzqb.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepo extends JpaRepository<Business, Integer> {
}
