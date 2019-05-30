package io.upepo.baharirestapi.controller;

import io.upepo.baharirestapi.exception.SystemRecordModifyException;
import io.upepo.baharirestapi.exception.ResourceNotFoundException;
import io.upepo.baharirestapi.model.Role;
import io.upepo.baharirestapi.payload.ListPayload;
import io.upepo.baharirestapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role)
    {
        roleRepository.save(role);

        return ResponseEntity.ok(role);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable(value="id") Long roleId,@Valid @RequestBody Role roleUpdated) throws ResourceNotFoundException, SystemRecordModifyException
    {
        Role roleInDB = roleRepository.findById(roleId).orElseThrow(()-> new ResourceNotFoundException(("Role not fouund")));

        if(roleInDB.getIsSystem())
        {
             throw new SystemRecordModifyException("Cannot Update System Role");
        }

        roleInDB.setName(roleUpdated.getName());
        roleInDB.setPrivileges(roleUpdated.getPrivileges());

        return ResponseEntity.ok(roleRepository.save(roleInDB));

    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value="id") Long roleId) throws ResourceNotFoundException
    {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        return ResponseEntity.ok(role);
    }

    @GetMapping("/roles")
    public ListPayload<Role> getAllRoles()
    {
        List<Role> roles= roleRepository.findAll();
        ListPayload<Role> rolesResponse = new ListPayload<Role>();
        rolesResponse.setContent(roles);
        return rolesResponse;
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?>deleteRole(@PathVariable(value="id") Long roleId) throws ResourceNotFoundException
    {
        Role role= roleRepository.findById(roleId).orElseThrow(()-> new ResourceNotFoundException("Role not found"));

        if(role.getIsSystem())
        {
            return ResponseEntity.badRequest().body("Cannot delete system role");
        }

        roleRepository.delete(role);

        return ResponseEntity.ok("Deleted");
    }
}
