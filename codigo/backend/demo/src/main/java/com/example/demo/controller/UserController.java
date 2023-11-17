package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDTONoPass;
import com.example.demo.service.UserService;

import java.util.List;

/**
 * A controller class that handles HTTP requests related to user operations.
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5174")
public class UserController {
    /** The service responsible for user-related operations. */
    @Autowired
    private UserService userService;

    /**
     * Creates a new user based on the provided user DTO.
     *
     * @param userDTO The user DTO containing user information.
     * @return A ResponseEntity containing the created user DTO.
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser= userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Updates an existing user with the provided user DTO.
     *
     * @param userId   The ID of the user to update.
     * @param userDTO  The user DTO containing updated user information.
     * @return A ResponseEntity containing the updated user DTO.
     */
    @PutMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser= userService.updateUser(userDTO, userId);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A list of UserDTO objects representing all users.
     */
    @GetMapping()
    @ResponseBody
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTONoPass> getUserById(@PathVariable("userId") Long userId) {
        UserDTONoPass user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    /**
     * Deletes a user with the specified user ID.
     *
     * @param userId The ID of the user to delete.
     * @return A message indicating the result of the deletion.
     */
    @DeleteMapping("/{userId}")
    @ResponseBody
    public String deleteUser(@PathVariable("userId") Long userId) {
        return userService.deleteUser(userId);
    }
}