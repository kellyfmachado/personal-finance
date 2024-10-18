package java_project.personal_finance.controller;

import java_project.personal_finance.service.AuthorizationService;
import java_project.personal_finance.dto.AuthenticationDto;
import java_project.personal_finance.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto authenticationDto){
        return authorizationService.login(authenticationDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody RegisterDto registerDto){
        return authorizationService.register(registerDto);
    }
}
