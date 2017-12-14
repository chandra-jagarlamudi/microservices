/**
 * 
 */
package com.ragas.boot.gateway.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chandra Jagarlamudi
 *
 */
@RestController
public class AuthenticationController {
	@GetMapping("/me")
	public Principal getMyUser(Principal principal) {
		return principal;
	}
}
