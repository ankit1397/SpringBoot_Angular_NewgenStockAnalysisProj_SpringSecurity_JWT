package com.springjwt.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springjwt.payload.request.LoginRequest;
import com.springjwt.payload.response.JwtResponse;
import com.springjwt.payload.response.MessageResponse;
import com.springjwt.security.jwt.JwtUtils;
import com.springjwt.services.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		if (!loginRequest.getPassword().equals("SolutionConsultant007")
				|| !loginRequest.getUsername().equals("NewgenEmployee")) {
			return ResponseEntity.badRequest().body(new MessageResponse("Wrong Credentials! Try again"));
		}
		final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUsername());
		final String jwtToken = jwtUtils.generateJwtToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(jwtToken));

	}

}
