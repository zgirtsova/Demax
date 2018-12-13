package dev.demax.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.demax.dtos.ChangePasswordDto;
import dev.demax.dtos.DashboardDto;
import dev.demax.dtos.ProfileDto;
import dev.demax.entities.User;
import dev.demax.services.UserService;

@Component
@RequestMapping("/")
public class UserController {
	private final UserService userService;
	private final BCryptPasswordEncoder bEncoder;
	
	@Autowired
	public UserController(UserService userService, BCryptPasswordEncoder bEncoder) {
		this.userService = userService;
		this.bEncoder = bEncoder;
	}
	
	@GetMapping("/")
	public DashboardDto dashboard(Principal principal) {
		return userService.getDashboard(principal.getName());
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@GetMapping("/profile")
	public ProfileDto profile(Principal principal) {
		return userService.getProfile(principal.getName());		
	}
	
	@PostMapping("/profile")
	public void profile(@ModelAttribute ChangePasswordDto dto, Principal principal) {
		User user = (User) userService.loadUserByUsername(principal.getName());
		if (bEncoder.matches(dto.getOldPassword(), user.getPassword())
						&& dto.getNewPassword().equals(dto.getRepeatPassword())) {
			
			user.setPassword(bEncoder.encode(dto.getNewPassword()));
		}
	}
	@GetMapping("/login?error")
	public String loginError() {
		return "login";
	}
}
