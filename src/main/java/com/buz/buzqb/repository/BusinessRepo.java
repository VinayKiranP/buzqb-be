package com.buz.buzqb.repository;

import com.buz.buzqb.entity.Business;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepo extends JpaRepository<Business, Long> {

  Optional<Business> findByEmail(String email) throws UsernameNotFoundException;


  @Query("SELECT b FROM Business b WHERE b.email = :email OR b.username = :username")
  Optional<Business> findByEmailOrUsername(@Param("email") String email, @Param("username") String username);

}
