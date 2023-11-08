package org.stand.springbootecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.stand.springbootecommerce.dto.UserDTO;
import org.stand.springbootecommerce.dto.request.AuthenticationRequest;
import org.stand.springbootecommerce.dto.request.RegisterRequest;
import org.stand.springbootecommerce.dto.response.AuthenticationResponse;
import org.stand.springbootecommerce.dto.response.BaseResponseBody;
import org.stand.springbootecommerce.error.BaseException;
import org.stand.springbootecommerce.error.UserNotAuthenticatedException;
import org.stand.springbootecommerce.error.UserNotFoundException;
import org.stand.springbootecommerce.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthenticationController {

    private final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationService authenticationService;
    private final ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<BaseResponseBody> register(
            @Valid @RequestBody RegisterRequest request
    ) throws BaseException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ) throws BadCredentialsException, BaseException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authenticationService.authenticate(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me () throws UserNotFoundException, UserNotAuthenticatedException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(modelMapper.map(authenticationService.me(), UserDTO.class));
    }

}
