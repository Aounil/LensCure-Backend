package org.soramed.LensCure.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// will have 2 end points that will allow me to creat or register a new acc or authenticate an existing user
@RestController
@RequestMapping("/api/v1/auth")//It sets a base URL for all the endpoints defined in the authenticationController class. Any request mapped in this controller will start with /api/v1/auth.
@RequiredArgsConstructor
@CrossOrigin



public class AuthenticationController {


    private final AuthenticationService service;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.Authenticate(request));   
    }
}
