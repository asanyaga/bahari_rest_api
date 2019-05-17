package io.upepo.baharirestapi.controller;

import io.upepo.baharirestapi.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class PrivilegeController {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @GetMapping("/privileges")
    public ResponseEntity<?> findAllPrivileges()
    {
        return ResponseEntity.ok(privilegeRepository.findAll());
    }
}
