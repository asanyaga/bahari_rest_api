package io.upepo.baharirestapi.controller;

import io.upepo.baharirestapi.exception.EmailExistsException;
import io.upepo.baharirestapi.exception.InvalidUserNamePasswordException;
import io.upepo.baharirestapi.exception.ResourceNotFoundException;
import io.upepo.baharirestapi.exception.UserNameExistsException;
import io.upepo.baharirestapi.model.Role;
import io.upepo.baharirestapi.model.User;
import io.upepo.baharirestapi.model.RoleName;

import io.upepo.baharirestapi.payload.ChangePasswordDTO;
import io.upepo.baharirestapi.payload.LoginDTO;
import io.upepo.baharirestapi.repository.UserRepository;
import io.upepo.baharirestapi.repository.RoleRepository;
import io.upepo.baharirestapi.security.JwtTokenProvider;
import io.upepo.baharirestapi.payload.JwtAuthenticationResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * Get all users list.
     *
     * @return the list
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    /**
     * Gets users by id.
     *
     * @param userId the user id
     * @return the users by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        return ResponseEntity.ok().body(user);
    }
    /**
     * Create user user.
     *
     * @param user the user
     * @return the user
     */
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) throws UserNameExistsException, EmailExistsException, ResourceNotFoundException {

        List<User> duplicateUser = userRepository.findByusername(user.getUserName());

        List<User> duplicateEmail = userRepository.findByemail(user.getEmail());

         if(duplicateUser.size()>0)
         {
             throw new UserNameExistsException("User name already exists");
         }

         if(duplicateEmail.size()>0)
         {
             throw new EmailExistsException("Email already exists");
         }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new ResourceNotFoundException ("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

         User newUser= userRepository.save(user);

         return ResponseEntity.ok(newUser);
    }
    /**
     * Update user response entity.
     *
     * @param userId the user id
     * @param userDetails the user details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */

    @PutMapping("/changepassword")
    public ResponseEntity<ChangePasswordDTO> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDetails) throws  ResourceNotFoundException
    {
        User user =
                userRepository
                        .findById(changePasswordDetails.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + changePasswordDetails.getUserId()));

        if(user.getPassword().equals(changePasswordDetails.getOldPassword()))
        {
            user.setPassword(changePasswordDetails.getNewPassword());
            userRepository.save(user);
            return  ResponseEntity.ok(changePasswordDetails);

        }
        else
        {
            return ResponseEntity.badRequest().body(changePasswordDetails);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login (@Valid @RequestBody LoginDTO loginDetails) throws InvalidUserNamePasswordException
    {
        List<User> users = userRepository.findByusername(loginDetails.getUserName());

        if(users.size()==0)
        {
          throw  new InvalidUserNamePasswordException("Invalid user name or password");
        }

        User user= users.get(0);

      if(!loginDetails.getPassword().equals(user.getPassword()))
        {
            throw new InvalidUserNamePasswordException("Invalid user name or password");

        }

        return  ResponseEntity.ok(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        user.setEmail(userDetails.getEmail());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        user.setUpdatedAt(new Date());
        user.setUserType(userDetails.getUserType());
        user.setUserName(userDetails.getUserName());
        user.setPassword(userDetails.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    /**
     * Delete user map.
     *
     * @param userId the user id
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
