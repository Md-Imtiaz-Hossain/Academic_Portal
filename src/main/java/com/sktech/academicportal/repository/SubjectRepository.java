package com.sktech.academicportal.repository;


import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
