package io.upepo.baharirestapi.controller;

import io.upepo.baharirestapi.exception.ResourceNotFoundException;
import io.upepo.baharirestapi.model.Connection;
import io.upepo.baharirestapi.repository.ConnectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ConnectionController {

    @Autowired ConnectionRepository connectionRepository;

    @GetMapping("/connections")
    @PreAuthorize("hasAuthority('CONNECTION_READ')")
    public ResponseEntity<?> getAllConnections()
    {
        return ResponseEntity.ok(connectionRepository.findAll());
    }

    @GetMapping("/connections/{id}")
    @PreAuthorize("hasAuthority('CONNECTION_READ')")
    public ResponseEntity<?> getConnectionById(@PathVariable(value="id") Long connectionId) throws ResourceNotFoundException
    {
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(() -> new ResourceNotFoundException("Connection not found"));

                return ResponseEntity.ok(connection);
    }

    @PostMapping("/connections")
    @PreAuthorize("hasAuthority('CONNECTION_CREATE')")
    public ResponseEntity<?> createConnection(@Valid @RequestBody Connection newConnection)
    {
        return ResponseEntity.ok(connectionRepository.save(newConnection));
    }

    @PutMapping("/connections/{id}")
    @PreAuthorize("hasAuthority('CONNECTION_UPDATE')")
    public ResponseEntity<?> updateConnection(@Valid @RequestBody Connection updatedConnection, @PathVariable(value="id") Long connectionId) throws ResourceNotFoundException
    {
        Connection connectionInDb = connectionRepository.findById(connectionId).orElseThrow(() -> new ResourceNotFoundException("Connection not found"));

        connectionInDb.setCustomer(updatedConnection.getCustomer());
        connectionInDb.setTenure(updatedConnection.getTenure());
        connectionInDb.setClassification(updatedConnection.getClassification());
        connectionInDb.setPlotNumber(updatedConnection.getPlotNumber());
        connectionInDb.setCounty(updatedConnection.getCounty());
        connectionInDb.setSubcounty(updatedConnection.getSubcounty());
        connectionInDb.setLocation(updatedConnection.getLocation());
        connectionInDb.setSubLocation(updatedConnection.getSubLocation());
        connectionInDb.setTown(updatedConnection.getTown());
        connectionInDb.setZone(updatedConnection.getZone());
        connectionInDb.setLatitude(updatedConnection.getLatitude());
        connectionInDb.setLongitude(updatedConnection.getLongitude());

        connectionRepository.save(connectionInDb);

        return ResponseEntity.ok(connectionInDb);

    }

    @DeleteMapping("/connections/{id}")
    public ResponseEntity<?> deleteConnection(@PathVariable(value="id") Long connectionId) throws ResourceNotFoundException
    {
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(()-> new ResourceNotFoundException("Connection not found"));

        connectionRepository.delete(connection);

        return ResponseEntity.ok("Deleted");
    }


}
