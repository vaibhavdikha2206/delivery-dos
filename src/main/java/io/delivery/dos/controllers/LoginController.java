package io.delivery.dos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.delivery.dos.models.user.Profile;
import io.delivery.dos.models.user.response.LoginResponseObject;
import io.delivery.dos.models.user.response.ProfileResponse;
import io.delivery.dos.models.vendor.Vendors;
import io.delivery.dos.repositories.user.CustomUserRepository;
import io.delivery.dos.repositories.user.ProfileRepository;
import io.delivery.dos.repositories.vendors.VendorsRepository;
import io.delivery.dos.security.models.AuthenticationRequest;
import io.delivery.dos.security.models.AuthenticationResponse;
import io.delivery.dos.security.services.MyUserDetails;
import io.delivery.dos.security.services.MyUserDetailsService;
import io.delivery.dos.security.util.JwtUtil;

@RestController
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			System.out.println("ok "+authenticationRequest.getUsername()+","+authenticationRequest.getPassword());
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
			System.out.println("VIOLA "+authenticationRequest.getUsername()+","+authenticationRequest.getPassword());
			
		}
		catch (BadCredentialsException e) {
			System.out.println("error");
			throw new Exception("Incorrect username or password", e);
		}

		System.out.println("1");
		
		final MyUserDetails userDetails = (MyUserDetails) userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		
		System.out.println("2"+userDetails.getName());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		System.out.println("3");
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}
