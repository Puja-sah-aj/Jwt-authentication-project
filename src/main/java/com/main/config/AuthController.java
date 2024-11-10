package com.main.config;

import com.main.entity.UserRequest;
import com.main.entity.UserResponse;
import com.main.security.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest request) {
        // Authenticate the user
        doAuthenticate(request.getEmail(), request.getPassword());

        // If authentication succeeds, generate a JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = helper.generateToken(userDetails);

        /// Build and return the response
        UserResponse response = UserResponse.builder()
                .token(token)
                .username(userDetails.getUsername())
                .build();
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
         UsernamePasswordAuthenticationToken authentition =   new UsernamePasswordAuthenticationToken(email, password);
         try {
        	 manager.authenticate(authentition);
        } catch (BadCredentialsException e) {
            logger.error("Invalid Username or Password", e);
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    	public String exceptionHandler() {
    		return "credentals invalid !!";
    }
}
    

