package com.openmarket.hms.config;



import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.openmarket.hms.domain.User;
import com.openmarket.hms.repository.UserRepository;
import com.openmarket.hms.services.JwtService;
import com.openmarket.hms.services.UserService;

import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter implements HandshakeInterceptor {
	@Autowired
	UserService userService;
	@Autowired
	JwtService jwtService;
	@Autowired
	UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, UnsupportedJwtException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String id = null;
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			log.warn("Header Name: {}, Header Value: {}", headerName, headerValue);
		}
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			if (token != null) {
				try {
					id = jwtService.extractUsername(token);

				} catch (UnsupportedJwtException e) {
					throw e;
				} catch (Exception e) {
					throw e;
				}

			}
		}
		System.out.println("userId"+id);
		if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				User userDetails = (User) userService.loadUserByUsername(id);
				if (userDetails != null && this.jwtService.validateToken(token, userDetails)) {
					
					
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, null);
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authToken);
					

	

				}
			} catch (Exception e) {
				System.err.println(e.getMessage());

			}

		}
		filterChain.doFilter(request, response);

	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if (request.getHeaders().getUpgrade().equalsIgnoreCase("websocket")) {
	        return true;
	    }
		String authHeader = request.getHeaders().getFirst("Authorization");
		String token = null;
		String id = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			if (token != null) {
				try {
					id = jwtService.extractUsername(token);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		System.out.println("id"+id);

		if (id != null) {
			try {
//				Optional<User> user = this.userService.findUserWallet(id);
				User userDetails = (User) userService.loadUserByUsername(id);
				if (userDetails != null && this.jwtService.validateToken(token, userDetails)) {
					System.out.println("this is do internal");
					attributes.put("principal", userDetails);
					return true;
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());

			}

		}
		
		


		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {

		// TODO Auto-generated method stub

	}

}


