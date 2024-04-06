package com.example.jpaannounce.repository;

import com.example.jpaannounce.entity.Announce;
import org.springframework.data.repository.CrudRepository;

public interface AnnounceRepository extends CrudRepository<Announce, Long> {
    /*@Query("SELECT DISTINCT a FROM Announce a WHERE a.departmentId =:departmentId")
    List<Announce> findByStudentDepartmentId(@Param("departmentId") Long departmentId);*/
}
