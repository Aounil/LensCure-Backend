package org.soramed.LensCure.controllers;

import org.soramed.LensCure.User.Role;
import org.soramed.LensCure.User.User;
import org.soramed.LensCure.User.UserRepository;
import org.soramed.LensCure.auth.AuthenticationResponse;
import org.soramed.LensCure.auth.AuthenticationService;
import org.soramed.LensCure.auth.RegistreRequest;
import org.soramed.LensCure.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PutMapping("/update/role/{id}")
    public ResponseEntity<String> updateRole(@PathVariable int id, @RequestBody Role role) {
        Optional<User> Opuser = userRepository.findById(id);

        if (Opuser.isPresent()) {
            User user = Opuser.get();
            user.setRole(role);
            userRepository.save(user);
        }
        return ResponseEntity.ok("the user's role is updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Optional<User> Opuser = userRepository.findById(id);
        User user = Opuser.get();
        userRepository.delete(user);
        return ResponseEntity.ok("the user has been deleed");
    }

    @GetMapping("/Users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

}
