package io.delivery.dos.vendorDeliveryControllers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

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

import com.google.firebase.messaging.FirebaseMessagingException;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.delivery.userdeliveries.GetDeliveriesListResponse;
import io.delivery.dos.models.user.Profile;
import io.delivery.dos.models.user.ProfileWithReferralCode;
import io.delivery.dos.models.user.request.RefreshTokenObject;
import io.delivery.dos.models.user.request.SignUpObject;
import io.delivery.dos.models.user.response.TokenUpdatedResponseObject;
import io.delivery.dos.models.user.response.UserCreditsResponse;
import io.delivery.dos.repositories.user.ProfileRepository;
import io.delivery.dos.repositories.user.ProfileRepositoryWithReferralCode;
import io.delivery.dos.repositories.user.SignUpRepository;
import io.delivery.dos.repositories.user.UpdateTokenRepository;
import io.delivery.dos.security.models.AuthenticationRequest;
import io.delivery.dos.security.models.AuthenticationResponse;
import io.delivery.dos.security.services.MyUserDetails;
import io.delivery.dos.security.services.MyUserDetailsService;
import io.delivery.dos.security.util.JwtUtil;
import io.delivery.dos.utils.NotifUtil;

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
	private ProfileRepositoryWithReferralCode profileRepositoryWithReferralCode;
	
	@Autowired
	private UpdateTokenRepository updateTokenRepository;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	private NotifUtil notifUtil;
	
	@Autowired
	private Environment env;
	
	private static final String keySign = "abcxyz";
	private static final float defaultCreds = 25;
	private static final float defaultZeroCreds = 0;
	
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

	@Transactional
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
		//if its from referral code add credits to profile and then register 
		
		Profile profile ;

		if( checkReferralCodeValidity(signUpObject.getReferralcode()) ){
			profile = new Profile(signUpObject.getUserid(),signUpObject.getName(),signUpObject.getPassword(),signUpObject.getRole(),signUpObject.getEmail(),defaultCreds,getReferralCode(signUpObject.getName()));	
		}
		else {
			profile = new Profile(signUpObject.getUserid(),signUpObject.getName(),signUpObject.getPassword(),signUpObject.getRole(),signUpObject.getEmail(),defaultZeroCreds,getReferralCode(signUpObject.getName()));	
		}

		return signUpRepository.save(profile);	
	}
	
	private void addCreditsToReferrer(ProfileWithReferralCode profileWithReferralCode) throws FirebaseMessagingException{
		// add 25 credits to this userid
		profileRepositoryWithReferralCode.updateProfileCredits(profileWithReferralCode.getUserid());
		notifUtil.sendNotificationToUserForCreditsUpdated(profileWithReferralCode.getUserid(),"25",profileWithReferralCode.getToken(),Constants.user_notification_Credits_updated);
	}
	
	private String getReferralCode(String userid) {
	
		Random r = new Random();
		int low = 1000;
		int high = 9999;
		int result = r.nextInt(high-low) + low;
		
		return (userid.substring(0,3)).concat(String.valueOf(result));
		
	}

	private boolean checkReferralCodeValidity(String referralcode) throws FirebaseMessagingException{
		
		ProfileWithReferralCode profileWithReferralCode = profileRepositoryWithReferralCode.findCountByReferralcodeCustom(referralcode);
		
		if(profileWithReferralCode!=null) {
			// also give credits to the person whose referral code is being used 
			addCreditsToReferrer(profileWithReferralCode);
			return true;
		}

		return false;
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
	
	@RequestMapping(method=RequestMethod.GET,value="/getRefCodeAndCredits")
	public UserCreditsResponse getCurrentCredits(@RequestHeader (name="Authorization") String authorizationHeader) { 
		// get drivers engaged during requested time
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        ProfileWithReferralCode profile = profileRepositoryWithReferralCode.findByUseridCustom(userid);
        return new UserCreditsResponse(profile.getCredits(),profile.getReferralcode());
	}
	private void getCurrentTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		ZoneId zone = ZoneId.of("Asia/Kolkata");
		LocalTime now = LocalTime.now(zone);
		//LocalDateTime now = LocalDateTime.now().plusMinutes(330);  
		System.out.println("Current Time is "+dtf.format(now));  	
	}
	

	
}
