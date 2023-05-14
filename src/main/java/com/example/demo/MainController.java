package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    AuthenticationManager authenticationManager;
    @GetMapping("/api/user")
    public Map<String, String> user(@AuthenticationPrincipal Principal principal) { // TODO Principal is null even if
        // the auth header is present in the request
        Map<String, String> result = new HashMap<>();
        result.put("username", principal.getName());
        return result;
    }

    @GetMapping("/api/home")
    public Map<String, String> home() {
        Map<String, String> result = new HashMap<>();
        result.put("hello", "world!");
        return result;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody LoginDto loginDto) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(),
                loginDto.password()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
