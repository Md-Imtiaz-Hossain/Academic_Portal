package com.sktech.academicportal.security;


import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if (user != null) {
            return new UserDetails(user);
        }
        throw new UsernameNotFoundException("Could not find user with email: " + email);
    }
}
