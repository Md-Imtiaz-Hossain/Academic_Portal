package com.sktech.academicportal.repository;


import com.sktech.academicportal.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    @Query("Select u from User u Where u.email = :email") //we can remove then spring find by auto config
    User getUserByEmail(@Param("email") String email); // can user 'get'/'find'

    Long countById(Integer id);

    /*@Query("select u from User u where lower(u.firstName) LIKE lower(concat('%', ?1,'%'))" +
            " or lower(u.lastName) LIKE lower(concat('%', ?1,'%'))" +
            " or u.email like %?1%")*/
    @Query("select u from User u where lower(concat(u.id, ' ', u.email, ' ', u.firstName, ' ', u.lastName))" +
            " like lower(concat('%', ?1,'%'))")
    Page<User> findAll(String keyword, Pageable pageable);

    @Query("Update User u set u.enabled = :enabled where u.id = :id")
    @Modifying
    void updateEnabledStatus(Integer id, boolean enabled);
}
