package org.stand.springbootecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stand.springbootecommerce.dto.UserDTO;
import org.stand.springbootecommerce.dto.request.UserUpdateRequest;
import org.stand.springbootecommerce.error.UserNotAuthenticatedException;
import org.stand.springbootecommerce.error.UserNotFoundException;
import org.stand.springbootecommerce.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PatchMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserUpdateRequest updatedUser) throws UserNotFoundException, UserNotAuthenticatedException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(userService.updateUser(updatedUser), UserDTO.class));
    }
}
