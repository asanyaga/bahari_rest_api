package io.upepo.huizhongmdmimport.controller;
import io.upepo.baharirestapi.exception.ResourceNotFoundException;
import io.upepo.baharirestapi.model.Customer;
import io.upepo.baharirestapi.repository.CustomerRepository;

import io.upepo.huizhongmdmimport.model.MDMMeterData;
import io.upepo.huizhongmdmimport.repository.MDMMeterDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class MDMMeterDataController {

    @Autowired
    MDMMeterDataRepository meterDataRepository;

    @GetMapping("/mdm/{id}")
    public ResponseEntity<?> getMeterDataByMeterCode(@PathVariable(value="id") Long meterID) throws ResourceNotFoundException
    {

        MDMMeterData mdm = meterDataRepository.findById(meterID).orElseThrow(() -> new ResourceNotFoundException("MDM Data not found"));

        return ResponseEntity.ok(mdm);
    }
}
