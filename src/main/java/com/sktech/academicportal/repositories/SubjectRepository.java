package com.sktech.academicportal.repositories;


import com.sktech.academicportal.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
