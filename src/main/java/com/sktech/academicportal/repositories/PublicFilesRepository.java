package com.sktech.academicportal.repositories;

import com.sktech.academicportal.entity.PublicFiles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicFilesRepository extends JpaRepository<PublicFiles, Long> {
    List<PublicFiles> findAllByType(String type);
}
