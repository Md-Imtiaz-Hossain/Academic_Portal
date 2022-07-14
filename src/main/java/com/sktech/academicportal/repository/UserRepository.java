package com.sktech.academicportal.repository;


import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("Select u from User u Where u.email = :email")
    User getUserByEmail(@Param("email") String email);

    @Query("Select u.subjects from User u Where u.email = :email")
    List<Subject> getSubjectByEmail(@Param("email") String email);

}
