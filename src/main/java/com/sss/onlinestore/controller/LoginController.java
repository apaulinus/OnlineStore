package com.sss.onlinestore.controller;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sss.onlinestore.model.Login;
import com.sss.onlinestore.model.Role;
import com.sss.onlinestore.response.LoginResponse;
import com.sss.onlinestore.security.JwtGenerator;
import com.sss.onlinestore.security.model.JwtUser;
import com.sss.onlinestore.service.LoginService;

@RestController
@RequestMapping(value = "/api/rest/logins")
public class LoginController {
	Logger LOG = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;
	@Autowired
	JwtGenerator jwtGenerator;

	@PostMapping()
	public ResponseEntity<LoginResponse> login(@RequestBody Login login) {
		LoginResponse loginResponse = new LoginResponse();
		Assert.notNull(login, "Login can not be null.");
		Assert.hasText(login.getUserName(), "User name must have a value.");
		Assert.hasText(login.getPassword(), "Password must have a value.");
	
		try {
			LOG.debug("About to login.");
			loginResponse = loginService.login(login);
			LOG.debug("Completed login.");

		} catch (Exception e) {
			LOG.error("Unknown exception occured", e);
			loginResponse.addError("Unknown server error occured, please check your login credentials and try again.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loginResponse);
		}

		if(loginResponse.getSuccess()) {
			LOG.info("Succesful login for user. {} {}", loginResponse.getFirstName(), loginResponse.getLastName());
			JwtUser jwtUser = new JwtUser();
			jwtUser.setUserName(login.getUserName());
			StringBuilder loginRole = new StringBuilder();
			
			
			if(login.getAuthorities() != null) {
				Role[] roles = (Role[])login.getAuthorities().toArray();
				for(Role role : roles) {
					loginRole.append(role.getRole());
					if(!role.equals(roles[roles.length-1])) {
						loginRole.append(",");
					}
				}
			jwtUser.setRole(loginRole.toString());
			}
			jwtUser.setId(login.getId());
			String token = this.generate(jwtUser);
			
			loginResponse.setToken(token);
			loginResponse.setUserName(login.getUserName());
			loginResponse.setAuthorities(login.getAuthorities());
			return ResponseEntity.ok(loginResponse);
		}
		else {
			loginResponse.addError("User name or password is incorrect, please re-enter user name and password.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
		}
	}
	
	@GetMapping()
	public String getLogins(){
		MDC.put("Get", "In getLogins()");
		
		return ("Successfully got here.");
	}

	/**
	 * This method is only used by those with admin role
	 */
	@PreAuthorize("hasAnyRole(ADMIN)")
	public String test() {
		return "Secured Hello";
	}

	private String generate(final JwtUser jwtUser) {
		return jwtGenerator.generate(jwtUser);
	}
}
