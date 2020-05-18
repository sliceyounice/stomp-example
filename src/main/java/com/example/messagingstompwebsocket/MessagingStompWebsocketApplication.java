package com.example.messagingstompwebsocket;

import com.example.messagingstompwebsocket.security.JwtTokenProvider;
import com.example.messagingstompwebsocket.security.SecurityConfig;
import com.example.messagingstompwebsocket.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Slf4j
public class MessagingStompWebsocketApplication {

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	SecurityConfig securityConfig;

	public static void main(String[] args) {
		SpringApplication.run(MessagingStompWebsocketApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args ->{
			Authentication authentication = securityConfig.authenticationManagerBean().authenticate(
					new UsernamePasswordAuthenticationToken(
							"roflan", "roflan"
					)
			);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = tokenProvider.generateToken(authentication);

			log.info(jwt);

			UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
}
