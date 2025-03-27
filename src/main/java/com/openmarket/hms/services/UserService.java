package com.openmarket.hms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openmarket.hms.domain.Role;
import com.openmarket.hms.domain.User;
import com.openmarket.hms.domain.UserPassword;
import com.openmarket.hms.repository.UserPasswordRepository;
import com.openmarket.hms.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
   @Autowired
   UserRepository userRepository;
   @Autowired
   UserPasswordRepository userPasswordRepository;
   
   
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOpt = this.userRepository.findById(username);
		
		if(userOpt.isPresent()) {
			return userOpt.get();
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	public Optional<Role> getUserRoleByUserId(String userId){
		return null;
	}
	
	public boolean findPermissionByRoleName(Optional<Role> role, Object permission) {
		return false;
	}
	
	public void createSuperUser(String phone) {
		Optional<User> userOpt =  this.userRepository.findByMobileNumber(phone);
		if(userOpt.isEmpty()) {
			var encodedPass =  new BCryptPasswordEncoder().encode("admin1234$1");
			var user = User.builder()
					.firstName("Admin")
					.lastName("Admin")
					.email("admin@openmarket@gmail.com")
					.mobileNumber(phone)
					.isActive(true)
					.build();
			try {
			User savedUser =	this.userRepository.save(user);
				var userPasswordBuild =  UserPassword.builder()
						.active(true)
						.pin(encodedPass)
						.user(savedUser)
						.build();
				try {
					Optional<UserPassword> userPassOpt = this.userPasswordRepository.findByUser(savedUser);
					if(userPassOpt.isEmpty()) {
						this.userPasswordRepository.save(userPasswordBuild);
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	
	
	
	public Optional<User> findUserByPhone(String phone){
		return this.userRepository.findByMobileNumber(phone);
	}
   
}
