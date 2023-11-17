package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDTONoPass;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.entity.ProjectEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing users and their associated projects.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder;

    /**
     * Creates a new user based on the provided UserDTO.
     *
     * @param userDTO The UserDTO object containing user data.
     * @return A UserDTO representing the created user.
     */
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setLogin(userDTO.getEmail());

        // String encodedPassword= passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return userDTO;
    }

    /**
     * Updates an existing user based on the provided UserDTO and user ID.
     *
     * @param userDTO The UserDTO object containing updated user data.
     * @param userId  The ID of the user to be updated.
     * @return A UserDTO representing the updated user.
     * @throws ResourceNotFoundException If the user with the specified ID is not
     *                                   found.
     */
    public UserDTO updateUser(UserDTO userDTO, Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setLogin(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
            return userDTO;
        } else {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
    }

    private UserDTO converter(UserEntity userEntity) {
        UserDTO result = new UserDTO();
        result.setId(userEntity.getId());
        result.setName(userEntity.getName());
        result.setSurname(userEntity.getSurname());
        result.setEmail(userEntity.getUsername());
        result.setPassword(userEntity.getPassword());

        return result;
    }

    
    private UserDTONoPass converterNoPass(UserEntity userEntity) {
        UserDTONoPass result = new UserDTONoPass();
        result.setId(userEntity.getId());
        result.setName(userEntity.getName());
        result.setSurname(userEntity.getSurname());
        result.setLogin(userEntity.getUsername());

        return result;
    }

    /**
     * Retrieves all users and converts them to a list of UserDTO objects.
     *
     * @return A list of UserDTO objects representing the users.
     */
    public List<UserDTO> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public UserDTONoPass getUserById(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            return converterNoPass(user);
        } else {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
    }
    /**
     * Deletes a user and associated projects based on the user's ID.
     *
     * @param userId The ID of the user to be deleted.
     * @return A string indicating the success of the user deletion.
     */
    public String deleteUser(@PathVariable("userId") Long userId) {
        List<ProjectEntity> projects = projectRepository.getProjectsByUserId(userId);
        projectRepository.deleteAll(projects);
        userRepository.deleteById(userId);
        return "DELETED";
    }

}