package com.codewebprojects.ecom.controller;

import com.codewebprojects.ecom.dto.AuthenticationRequest;
import com.codewebprojects.ecom.dto.SignupRequest;
import com.codewebprojects.ecom.dto.UserDto;
import com.codewebprojects.ecom.entity.User;
import com.codewebprojects.ecom.repository.UserRepository;
import com.codewebprojects.ecom.services.auth.AuthService;
import com.codewebprojects.ecom.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController

public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil; // Assuming this class generates JWTs
    private final UserRepository userRepository; // Assuming this is needed for additional user data
private final AuthService authService;
public static final String TOKEN_PREFIX="Bearer";
public  static final String HEADER_STRING="Authorization";
    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JwtUtil jwtUtil,
                          UserRepository userRepository,
                          AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.authService = authService;
    }
    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
                                                       HttpServletResponse response) throws IOException, JSONException { // HttpServletResponse can be removed if not setting cookies
try {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
    );
}catch (BadCredentialsException e){
    throw new BadCredentialsException("Incorrect username or password");
}
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());


        final String jwt=jwtUtil.generateToken(userDetails.getUsername());
    if(optionalUser.isPresent()){
        response.getWriter().write(new JSONObject().put("userId",optionalUser.get().getId())
                .put("role",optionalUser.get().getRole())
                .toString());
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, " +
                "X-Requested-With, Content-Type, Accept, X-Custom-header");
        response.addHeader(HEADER_STRING,TOKEN_PREFIX + jwt);
    }
}
@PostMapping("/sign-up")
public ResponseEntity <?> signupUser(@RequestBody SignupRequest signupRequest){
if(authService.hasUserWithEmail(signupRequest.getEmail())){
    return new ResponseEntity<>("user alredy exists", HttpStatus.NOT_ACCEPTABLE);

}
UserDto userDto=authService.createUser(signupRequest);
return  new ResponseEntity<>(userDto,HttpStatus.OK);
}
}
