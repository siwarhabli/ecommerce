package com.codewebprojects.ecom.services;

import com.codewebprojects.ecom.repository.UserRepository;
import com.codewebprojects.ecom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList; // For creating an empty list of authorities
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findFirstByEmail(username); // Assuming 'com.example.demo.model.User' is your entity path

        if (optionalUser.isEmpty()) throw new UsernameNotFoundException("User not found with username: ", null);
            return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(), optionalUser.get().getPassword(), new ArrayList<>());



    }}