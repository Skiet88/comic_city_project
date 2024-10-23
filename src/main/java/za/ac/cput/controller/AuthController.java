package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.DTO.AuthResponse;
import za.ac.cput.DTO.AuthenticationRequest;
import za.ac.cput.service.JWTService;
import za.ac.cput.service.UserService.UserService;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authRequest) {
        try {
            // Authenticate user credentials
            System.out.println("Request body: " + authRequest);
            System.out.println("Sername: " + authRequest.getEmail());


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );


            System.out.println("validated: " + authentication.isAuthenticated());

            // If authentication is successful, get the authenticated user
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();  // No need to call loadUserByUsername again

            // Extract role from UserDetails
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .orElse("ROLE_USER");
            System.out.println("role:" + role);
            // Generate JWT token
            String jwtToken = jwtService.generateToken(userDetails.getUsername(), role);

            System.out.println("Generated JWT Token: " + jwtToken);

            String redirectUrl;
            switch (role) {
                case "ROLE_ADMIN":
                    redirectUrl = "/admin-layout";
                    break;
                case "ROLE_CUSTOMER":
                    redirectUrl = "/";
                    break;

                default:
                    redirectUrl = "/home";
            }


            AuthResponse authResponse = new AuthResponse.Builder()
                    .setToken(jwtToken)
                    .setRole(role)
                    .setRedirectUrl(redirectUrl)
                    .build();

            return ResponseEntity.ok(authResponse);

        } catch (Exception e) {
            // Handle authentication errors
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
