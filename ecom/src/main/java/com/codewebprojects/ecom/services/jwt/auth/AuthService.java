package com.codewebprojects.ecom.services.jwt.auth;

import com.codewebprojects.ecom.dto.SignupRequest;
import com.codewebprojects.ecom.dto.UserDto;
import org.springframework.stereotype.Service;


public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
    Boolean hasUserWithEmail(String email);
}
