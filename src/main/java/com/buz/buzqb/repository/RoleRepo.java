package com.buz.buzqb.repository;

import com.buz.buzqb.entity.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

  //@Query(value = "SELECT * FROM role WHERE priority NOT IN (:priorities)", nativeQuery = true)
  //List<Role> findByPriorityNotIn(@Param("priorities") List<Integer> priorities);

  @Query(value = "SELECT * FROM role WHERE priority > (SELECT priority from role where id =:roleId)", nativeQuery = true)
  List<Role> findByPriorityNotIn(@Param("roleId") Integer roleId);
}
