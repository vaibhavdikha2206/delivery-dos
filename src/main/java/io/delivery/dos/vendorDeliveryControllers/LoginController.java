package io.delivery.dos.vendorDeliveryControllers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.delivery.dos.models.user.Profile;
import io.delivery.dos.models.user.request.RefreshTokenObject;
import io.delivery.dos.models.user.request.SignUpObject;
import io.delivery.dos.models.user.response.TokenUpdatedResponseObject;
import io.delivery.dos.repositories.user.ProfileRepository;
import io.delivery.dos.repositories.user.SignUpRepository;
import io.delivery.dos.repositories.user.UpdateTokenRepository;
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

	@Autowired
	private SignUpRepository signUpRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UpdateTokenRepository updateTokenRepository;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	private Environment env;
	
	private static final String keySign = "abcxyz";
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			System.out.println("ok "+authenticationRequest.getUsername()+","+authenticationRequest.getPassword());
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			System.out.println("error in /Authenticate"+e);
			throw new Exception("Incorrect username or password", e);
		}
		
		final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);	
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Profile signUp(@RequestBody SignUpObject signUpObject) throws JpaSystemException , Exception {
		
		if(signUpObject.getSignkey().equals(keySign)) {	
		System.out.println("Signup "+signUpObject.getName()+","+signUpObject.getUserid()+","+signUpObject.getPassword()+","+signUpObject.getEmail());
		}
		else {
			throw new Exception("Sorry,cant Sign Up , Please Try Again");
		}

		if(profileRepository.findByUseridCustom(signUpObject.getUserid())!=null) {
			throw new Exception("Username Already Exists");
		}
		Profile profile = new Profile(signUpObject.getUserid(),signUpObject.getName(),signUpObject.getPassword(),signUpObject.getRole(),signUpObject.getEmail());
		return signUpRepository.save(profile);	
	}
	
	@Transactional
	@RequestMapping(value = "/updateToken", method = RequestMethod.POST)
	public TokenUpdatedResponseObject  updateToken(@RequestBody RefreshTokenObject refreshTokenObject,@RequestHeader (name="Authorization") String authorizationHeader) throws JpaSystemException , Exception {
		String jwt = authorizationHeader.substring(7);
		String userid = jwtUtil.extractUsername(jwt);
		System.out.println("token is "+refreshTokenObject.getToken()+","+userid);
		if(updateTokenRepository.updateToken(userid,refreshTokenObject.getToken())==1) {
		return new TokenUpdatedResponseObject(updateTokenRepository.updateToken(userid,refreshTokenObject.getToken()),"Updated");
		}
		return new TokenUpdatedResponseObject(0,"Error"); 
	}
	
	private void getCurrentTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		ZoneId zone = ZoneId.of("Asia/Kolkata");
		LocalTime now = LocalTime.now(zone);
		//LocalDateTime now = LocalDateTime.now().plusMinutes(330);  
		System.out.println("Current Time is "+dtf.format(now));  	
	}
	

	
}
