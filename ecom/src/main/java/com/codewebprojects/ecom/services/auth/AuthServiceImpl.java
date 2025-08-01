package com.codewebprojects.ecom.services.auth;

import com.codewebprojects.ecom.dto.SignupRequest;
import com.codewebprojects.ecom.dto.UserDto;
import com.codewebprojects.ecom.entity.User;
import com.codewebprojects.ecom.enums.UserRole;
import com.codewebprojects.ecom.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserDto createUser(SignupRequest singnupRequest){
User user=new User();
user.setEmail(singnupRequest.getEmail());
user.setName(singnupRequest.getName());
user.setPassword(new BCryptPasswordEncoder().encode(singnupRequest.getPassword()));
user.setRole(UserRole.CUSTOMER);
User createUser=userRepository.save(user);
UserDto userDto=new UserDto();
userDto.setId(createUser.getId());
return userDto;

    }
    public Boolean hasUserWithEmail(String email){
        return userRepository.findFirstByEmail(email).isPresent();
    }

@PostConstruct
    public void createAdminAccount(){

        User adminAccount=userRepository.findByRole(UserRole.ADMIN);
if(null== adminAccount)
{
    User user= new User();
user.setEmail("admin@test.com");
user.setName("admin");
user.setRole(UserRole.ADMIN);
user.setPassword(new BCryptPasswordEncoder().encode("admin"));
userRepository.save(user);
}
}

}
