package com.openmarket.hms.services;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openmarket.hms.domain.Department;
import com.openmarket.hms.domain.Role;
import com.openmarket.hms.domain.User;
import com.openmarket.hms.domain.UserPassword;
import com.openmarket.hms.repository.DepartmentRepository;
import com.openmarket.hms.repository.UserPasswordRepository;
import com.openmarket.hms.repository.UserRepository;
import com.openmarket.hms.requestDto.CreateUserDto;
import com.openmarket.hms.requestDto.UpdateUserDto;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserPasswordRepository userPasswordRepository;
	@Autowired
	DepartmentRepository deptRepository;

	public Object createUser(CreateUserDto userDto) {
		String mobile = userDto.getMobileNumber().trim();
		if(mobile.length() < 9) {
			Map<String,Object> res  = new HashMap<>();
			
			res.put("success",false);
			res.put("message","Invalid mobile number");
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
		String mobileNumber = "254"+mobile.substring(mobile.length() -9);
		Optional<User> userOpt = this.userRepository.findByMobileNumber(mobileNumber);
		if(userOpt.isPresent()) {
			Map<String,Object> res = new HashMap<>();
			res.put("success",false);
			res.put("message","User already exists");

			return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
		}

		Department dept = null;

		Optional<Department> deptOpt =  this.deptRepository.findById(userDto.getDepartmentId());
		if(deptOpt.isPresent()) {
			dept = deptOpt.get();
		}

		try {
			User userBuild =  User.builder().firstName(userDto.getFirstName()).middleName(userDto.getMiddleName())
					.lastName(userDto.getLastName()).dob(userDto.getDob()).idNumber(userDto.getIdNumber())
					.email(userDto.getEmail()).isActive(true).department(dept).mobileNumber(mobileNumber)
					.build();
			User user =  this.userRepository.save(userBuild);
			String password = new BCryptPasswordEncoder().encode(generateRandomPassword());
			UserPassword userPass =  UserPassword.builder().active(true).pin(password).user(user).build();
			
			try {
				this.userPasswordRepository.save(userPass);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
			Map<String,Object> res = new HashMap<>();
			res.put("success",true);
			res.put("message","User created!");

			return ResponseEntity.status(HttpStatus.OK).body(res);

		}catch(Exception ex) {
			ex.printStackTrace();
			Map<String,Object> res = new HashMap<>();
			res.put("success",false);
			res.put("message","Server error while processing request");

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}
	
	
	@Transactional
	public Object updateStaff(UpdateUserDto userDto) {
		Optional<User> userOpt =  this.userRepository.findById(userDto.getUserId());
		if(userOpt.isEmpty()) {
			Map<String,Object> res = new HashMap<>();
			res.put("success",false);
			res.put("message","Invalid userId");
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
		
		Optional<Department> deptOpt = this.deptRepository.findById(userDto.getDepartmentId());
		if(deptOpt.isEmpty()) {
			Map<String,Object> res = new HashMap<>();
			res.put("success",false);
			res.put("message","Invalid department");
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
		
		Department dept = deptOpt.get();
		User user = userOpt.get();
		user.setDepartment(dept);
		user.setDob(userDto.getDob());
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setMiddleName(userDto.getMiddleName());
		user.setLastName(userDto.getLastName());
		user.setIdNumber(userDto.getIdNumber());
		user.setIsActive(userDto.getIsActive());
	
		try {
			this.userRepository.save(user);
			Map<String,Object> res = new HashMap<>();
			res.put("success",true);
			res.put("message","User record updated");
			
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}catch(Exception ex) {
			Map<String,Object> res = new HashMap<>();
			res.put("success",false);
			res.put("message","Opps!Server error");
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}

   
   public static String generateRandomPassword() {
       String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
       SecureRandom random = new SecureRandom();
       StringBuilder password = new StringBuilder(6);
       for (int i = 0; i < 6; i++) {
           password.append(chars.charAt(random.nextInt(chars.length())));
       }
       return password.toString();
   }
   
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOpt = this.userRepository.findById(username);
		
		if(userOpt.isPresent()) {
			return userOpt.get();
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	public Optional<User> findUserByUserId(String id){
		return this.userRepository.findById(id);
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
