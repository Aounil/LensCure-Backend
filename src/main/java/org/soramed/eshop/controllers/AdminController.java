package org.soramed.eshop.controllers;

import com.fasterxml.jackson.annotation.OptBoolean;
import org.soramed.eshop.User.User;
import org.soramed.eshop.User.UserRepository;
import org.soramed.eshop.auth.AuthenticationResponse;
import org.soramed.eshop.auth.AuthenticationService;
import org.soramed.eshop.auth.RegistreRequest;
import org.soramed.eshop.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@PreAuthorize("hasAnyAuthority('ADMIN')")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final AuthenticationService service;

    public AdminController(EmailService emailService, UserRepository userRepository, AuthenticationService service) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistreRequest request) {
        emailService.sendEmail(request.getEmail(), "NEW MEMBER !!", "Welcome to our family " + request.getName());
        return ResponseEntity.ok(service.register(request));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<User> Opuser = userRepository.findById(id);
        User user = Opuser.get();
        userRepository.delete(user);
        return ResponseEntity.ok("the user has been deleed");
    }

}
