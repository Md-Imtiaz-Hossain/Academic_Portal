package com.sktech.academicportal.repositories;


import com.sktech.academicportal.entity.Profile;
import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {


}
