package com.vn.service;

import com.vn.dto.ReqRes;
import com.vn.entity.User;
import com.vn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try{
            User user = new User();
            user.setName(registrationRequest.getName());
            user.setEmail(registrationRequest.getEmail());
            user.setAvatar(registrationRequest.getAvatar());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setDob(registrationRequest.getDob());
            user.setPhoneNumber(registrationRequest.getPhoneNumber());
            user.setCity(registrationRequest.getCity());
            user.setDistrict(registrationRequest.getDistrict());
            user.setWard(registrationRequest.getWard());
            user.setPoint(0);
            user.setStatus(registrationRequest.getStatus());
            user.setRole(registrationRequest.getRole());
            User userResult = userRepository.save(user);
            if(userResult != null && userResult.getId()>0){
                resp.setUser(userResult);
                resp.setMessage("User save successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
    public ReqRes signIn(ReqRes signInRequest){
        ReqRes resp = new ReqRes();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getEmail(),signInRequest.getPassword()));
            var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow();
            System.out.println("USER IS: " + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            resp.setStatusCode(200);
            resp.setToken(jwt);
            resp.setRefreshToken(refreshToken);
            resp.setExpirationTime("24Hr");
            resp.setMessage("Successfully Signed In");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes resp = new ReqRes();
        String email = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(email).orElseThrow();
        if(jwtUtils.isTokenValid(refreshTokenRequest.getRefreshToken(), user)){
            var jwt = jwtUtils.generateToken(user);
            resp.setStatusCode(200);
            resp.setToken(jwt);
            resp.setRefreshToken(refreshTokenRequest.getToken());
            resp.setExpirationTime("24Hr");
            resp.setMessage("Successfully Refresh Token");
        }
        resp.setStatusCode(500);
        return resp;
    }
}
