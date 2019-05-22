package io.upepo.baharirestapi.controller;

import io.upepo.baharirestapi.exception.ResourceNotFoundException;
import io.upepo.baharirestapi.model.Zone;
import io.upepo.baharirestapi.repository.ZoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ZoneController {

    @Autowired ZoneRepository zoneRepository;

    @GetMapping("/zones")
    @PreAuthorize("hasAuthority('ZONE_READ')")
    public ResponseEntity<?> getAllZones()
    {
        return ResponseEntity.ok(zoneRepository.findAll());
    }

    @GetMapping("/zones/{id}")
    @PreAuthorize("hasAuthority('ZONE_READ')")
    public ResponseEntity<?> getZoneById(@PathVariable(value="id") Long zoneId) throws ResourceNotFoundException
    {
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(()-> new ResourceNotFoundException("Zone not found"));

        return ResponseEntity.ok(zone);
    }

    @PostMapping("/zones")
    @PreAuthorize("hasAuthority('ZONE_CREATE')")
    public ResponseEntity<?>createZone(@Valid @RequestBody Zone zone)
    {
        return ResponseEntity.ok(zoneRepository.save(zone));
    }

    @PutMapping("/zones/{id}")
    @PreAuthorize("hasAuthority('ZONE_UPDATE')")
    public ResponseEntity<?>updateZone(@PathVariable(value="id") Long zoneId,@Valid @RequestBody Zone updatedZone) throws ResourceNotFoundException
    {
        Zone zoneInDb = zoneRepository.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        zoneInDb.setName(updatedZone.getName());

        zoneRepository.save(zoneInDb);

        return ResponseEntity.ok(zoneInDb);
    }

    @DeleteMapping("/zones/{id}")
    @PreAuthorize("hasAuthority('ZONE_DELETE')")
    public ResponseEntity<?> deleteZone(@PathVariable(value="id") Long zoneId) throws ResourceNotFoundException
    {
        Zone zoneInDB = zoneRepository.findById(zoneId).orElseThrow(()-> new ResourceNotFoundException("Zone not found"));

        zoneRepository.delete(zoneInDB);

        return ResponseEntity.ok("Deleted");

    }
}
