package org.soramed.eshop.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.soramed.eshop.service.EmailService;


// will have 2 end points that will allow me to creat or register a new acc or authenticate an existing user
@RestController
@RequestMapping("/api/v1/auth")//It sets a base URL for all the endpoints defined in the authenticationController class. Any request mapped in this controller will start with /api/v1/auth.
@RequiredArgsConstructor
@CrossOrigin


public class AuthenticationController {

    private final AuthenticationService service;
    private final EmailService emailService;



    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.Authenticate(request));   
    }
}
