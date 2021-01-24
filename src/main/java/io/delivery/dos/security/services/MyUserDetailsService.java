package io.delivery.dos.security.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.delivery.dos.security.models.ProfileUser;
import io.delivery.dos.security.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("authentication for "+userid);
		Optional<ProfileUser> user = userRepository.findByUserid(userid);
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found " + userid));
		return user.map(MyUserDetails::new).get();
		
	}

}
