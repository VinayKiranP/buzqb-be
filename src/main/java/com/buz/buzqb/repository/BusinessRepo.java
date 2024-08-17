package com.buz.buzqb.repository;

import com.buz.buzqb.entity.Business;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepo extends JpaRepository<Business, Integer> {
  Optional<Business> findByEmail(String email) throws UsernameNotFoundException;
}
