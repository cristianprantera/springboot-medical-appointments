package com.testeando.turnos.controllers.views;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testeando.turnos.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")

public class AdminController {
	
	@GetMapping("/index")
	public String adminIndex() {
		return ViewRouteHelper.ADMIN_INDEX;
		
	}
	
	@GetMapping("/allAppointments")
	public String adminAllAppointments() {
		return ViewRouteHelper.ADMIN_APPOINTMENTS;
	}
}
