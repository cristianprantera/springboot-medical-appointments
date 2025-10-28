package com.testeando.turnos.controllers.views;

import com.testeando.turnos.helpers.ViewRouteHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

	@GetMapping("/redirectByRole")
	public String redirectByRole(Authentication auth) {
	    for (GrantedAuthority authority : auth.getAuthorities()) {
	        String role = authority.getAuthority();

	        switch (role) {
	            case "ROLE_ADMIN":
	                return "redirect:/admin/index";
	            case "ROLE_DOCTOR":
	                return "redirect:/doctor/index";
	            case "ROLE_PATIENT":
	                return "redirect:/patient/index";
	            default:
	                return "redirect:/auth/login?error";
	        }
	    }
	    return "redirect:/auth/login?error";
	}

}
