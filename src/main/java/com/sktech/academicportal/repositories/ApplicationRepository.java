package com.sktech.academicportal.repositories;


import com.sktech.academicportal.entity.AllApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<AllApplication, Integer> {


}
