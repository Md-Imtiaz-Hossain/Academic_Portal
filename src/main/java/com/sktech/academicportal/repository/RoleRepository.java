package com.sktech.academicportal.repository;


import com.sktech.academicportal.entity.Role;
import com.sktech.academicportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
