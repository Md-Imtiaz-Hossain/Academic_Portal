package com.sktech.academicportal.repository;


import com.sktech.academicportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("Select u from User u Where u.email = :email")
    User getUserByEmail(@Param("email") String email);

}
