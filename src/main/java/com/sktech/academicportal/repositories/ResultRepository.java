package com.sktech.academicportal.repositories;


import com.sktech.academicportal.entity.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<StudentResult, Integer> {

}
