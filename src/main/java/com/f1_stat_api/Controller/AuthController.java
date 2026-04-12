package com.f1_stat_api.Controller;

import com.f1_stat_api.DTO.AuthRequest;
import com.f1_stat_api.DTO.AuthResponse;
import com.f1_stat_api.Service.AuthServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthServices authServices;
    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest){
        return  ResponseEntity.ok(authServices.registry(authRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        return  ResponseEntity.ok(authServices.login(authRequest));
    }

}
