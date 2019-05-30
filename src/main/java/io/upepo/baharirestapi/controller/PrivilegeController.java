package io.upepo.baharirestapi.controller;

import io.upepo.baharirestapi.model.Privilege;
import io.upepo.baharirestapi.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class PrivilegeController {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @GetMapping("/privileges")
    public ResponseEntity<List<Privilege>> findAllPrivileges()
    {
        return ResponseEntity.ok(privilegeRepository.findAll());
    }
}
