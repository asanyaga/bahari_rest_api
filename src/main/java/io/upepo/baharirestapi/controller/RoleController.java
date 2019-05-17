package io.upepo.baharirestapi.controller;

import io.upepo.baharirestapi.exception.ResourceNotFoundException;
import io.upepo.baharirestapi.model.Role;
import io.upepo.baharirestapi.repository.RoleRepository;
import javafx.beans.property.ReadOnlyListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/roles")
    public ResponseEntity<?> createRole(@Valid @RequestBody Role role)
    {
        roleRepository.save(role);

        return ResponseEntity.ok(role);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<?> updateRole(@PathVariable(value="id") Long roleId,@Valid @RequestBody Role roleUpdated) throws ResourceNotFoundException
    {
        Role roleInDB = roleRepository.findById(roleId).orElseThrow(()-> new ResourceNotFoundException(("Role not fouund")));

        roleInDB.setName(roleUpdated.getName());
        roleInDB.setPrivileges(roleUpdated.getPrivileges());

        return ResponseEntity.ok(roleRepository.save(roleInDB));

    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable(value="id") Long roleId) throws ResourceNotFoundException
    {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        return ResponseEntity.ok(role);
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles()
    {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?>deleteRole(@PathVariable(value="id") Long roleId) throws ResourceNotFoundException
    {
        Role role= roleRepository.findById(roleId).orElseThrow(()-> new ResourceNotFoundException("Role not found"));

        roleRepository.delete(role);

        return ResponseEntity.ok("Deleted");
    }
}
