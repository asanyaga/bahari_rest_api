package io.upepo.baharirestapi.controller;

import io.upepo.baharirestapi.exception.ResourceNotFoundException;
import io.upepo.baharirestapi.model.Meter;
import io.upepo.baharirestapi.repository.MeterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class MeterController {
    @Autowired MeterRepository meterRepository;

    @PreAuthorize("hasAuthority('METER_READ')")
    @GetMapping("/meters")
    public ResponseEntity<?> getAllMeters()
    {
        return ResponseEntity.ok(meterRepository.findAll());
    }

    @PreAuthorize("hasAuthority('METER_READ')")
    @GetMapping("/meters/{id}")
    public ResponseEntity<?> getMeterById(@PathVariable(value ="id") Long meterId) throws ResourceNotFoundException
    {
        Meter meterInDb = meterRepository.findById(meterId).orElseThrow(() -> new ResourceNotFoundException("Meter not found"));

        return ResponseEntity.ok(meterInDb);
    }

    @PreAuthorize("hasAuthority('METER_CREATE')")
    @PostMapping("/meters")
    public ResponseEntity<?> createMeter(@Valid @RequestBody Meter meter)
    {
        return ResponseEntity.ok(meterRepository.save(meter));
    }

    @PreAuthorize("hasAuthority('METER_UPDATE')")
    @PutMapping("/meters/{id}")
    public ResponseEntity<?> updateMeter(@Valid @RequestBody Meter updatedMeter, @PathVariable(value="id") Long meterId) throws ResourceNotFoundException
    {
        Meter meterInDB = meterRepository.findById(meterId).orElseThrow(() -> new ResourceNotFoundException("Meter not found"));

        meterInDB.setMeterNumber(updatedMeter.getMeterNumber());
        meterInDB.setDeviceNumber(updatedMeter.getDeviceNumber());
        meterInDB.setImeiNumber(updatedMeter.getImeiNumber());
        meterInDB.setSize(updatedMeter.getSize());
        meterInDB.setManufacturer(updatedMeter.getManufacturer());
        meterInDB.setModel(updatedMeter.getModel());
        meterInDB.setType(updatedMeter.getType());
        meterInDB.setResetValue(updatedMeter.getResetValue());
        meterInDB.setInstallationDate(updatedMeter.getInstallationDate());
        meterInDB.setLatitude(updatedMeter.getLatitude());
        meterInDB.setLongitude(updatedMeter.getLongitude());
        meterInDB.setConnection(updatedMeter.getConnection());

        meterRepository.save(meterInDB);

        return ResponseEntity.ok(meterInDB);
    }

    @PreAuthorize("hasAuthority('METER_DELETE')")
    @DeleteMapping("/meters/{id}")
    public ResponseEntity<?>deleteMeter(@PathVariable(value = "id") Long meterId) throws ResourceNotFoundException
    {
        Meter meter = meterRepository.findById(meterId).orElseThrow(()-> new ResourceNotFoundException("Meter not found"));

        meterRepository.delete(meter);

        return ResponseEntity.ok("Deleted");

    }
}
