package com.f1_stat_api.Service;

import com.f1_stat_api.DTO.AuthRequest;
import com.f1_stat_api.DTO.AuthResponse;
import com.f1_stat_api.Security.JwtUtil;
import com.f1_stat_api.model.User;
import com.f1_stat_api.repositary.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServices(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public AuthResponse registry(AuthRequest response){
        if(userRepository.existsByEmail(response.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        User user =new User();
        user.setEmail(response.getEmail());
        user.setName(response.getName());
        user.setPassword(passwordEncoder.encode(response.getPassword()));
        userRepository.save(user);
        String token=jwtUtil.generateToken(response.getEmail());
        return new AuthResponse(token,user.getName(), user.getEmail());
    }

    public AuthResponse login(AuthRequest response){
        User user=userRepository.findByEmail(response.getEmail()).orElseThrow(()-> new RuntimeException("Email not found"));
        if(!passwordEncoder.matches(response.getPassword(),user.getPassword())){
            throw new RuntimeException("Passwords do not match");
        }
        String token=jwtUtil.generateToken(response.getEmail());
        return new AuthResponse(token,user.getName(), user.getEmail());
    }

}
