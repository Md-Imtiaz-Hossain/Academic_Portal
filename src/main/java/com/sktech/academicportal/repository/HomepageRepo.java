package com.sktech.academicportal.repository;

import com.sktech.academicportal.entity.HomepageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomepageRepo extends JpaRepository<HomepageEntity, Long> {

    HomepageEntity findBySection(String section);
}
