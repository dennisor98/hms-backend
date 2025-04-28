package com.openmarket.hms.aspects;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openmarket.hms.domain.User;
import com.openmarket.hms.enums.JwtType;
import com.openmarket.hms.services.JwtService;
import com.openmarket.hms.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@Aspect
@Component
@Slf4j
public class RefreshTokenMiddlewareAspect {
	
	private JwtService jwtService;
	private UserService userService;

	public RefreshTokenMiddlewareAspect(UserService userService, JwtService jwtService) {
		this.userService = userService;

		this.jwtService = jwtService;
	}

	@Before("@annotation(com.openmarket.hms.annotations.RefreshTokenMiddleware)")
	public void beforeControllerMethodExecution() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		var refreshTokenHeader = request.getHeader("refresh-token-header");
		if (refreshTokenHeader == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "refresh token header required");
			map.put("success", false);
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String jsonError = objectMapper.writeValueAsString(map);
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, jsonError);

			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			var id = this.jwtService.extractUsername(refreshTokenHeader,JwtType.REFRESH_TOKEN);
			log.error("refreshTokenHeader" + id);

			// try {
//					Optional<User> user = this.userService.findUserWallet(id);
			Optional<User> userDetails = userService.findUserByUserId(id);
			if(userDetails.isPresent()) {
				if (this.jwtService.validateToken(refreshTokenHeader, userDetails.get(), JwtType.REFRESH_TOKEN)) {
					Optional<User> clonedUserDetails = Optional.of(userDetails.get());

					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
							clonedUserDetails.get(), null, null);
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("message", "refresh token header required");
				map.put("success", false);
				ObjectMapper objectMapper = new ObjectMapper();
				String jsonError = objectMapper.writeValueAsString(map);
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, jsonError);
			}
			
			

		}
		
	}

	}
