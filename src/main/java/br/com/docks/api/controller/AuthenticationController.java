package br.com.docks.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.docks.api.domain.User;
import br.com.docks.api.domain.dto.TokenDto;
import br.com.docks.api.domain.form.LoginForm;
import br.com.docks.api.service.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	
	@Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginForm loginForm) {
        try {
        	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());
            Authentication authentication = manager.authenticate(authenticationToken);

            String tokenJWT = tokenService.getToken((User) authentication.getPrincipal());
            
            User user = (User) authentication.getPrincipal();

            return ResponseEntity.ok(new TokenDto(tokenJWT, "Bearer", user.getName()));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    	
    

    }

}
