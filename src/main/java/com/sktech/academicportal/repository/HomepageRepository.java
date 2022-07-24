package com.sktech.academicportal.repository;

import com.sktech.academicportal.entity.HomepageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomepageRepository extends JpaRepository<HomepageEntity, Long> {

    HomepageEntity findBySection(String section);
}