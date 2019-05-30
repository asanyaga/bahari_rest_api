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
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class MDMMeterDataController {

    @Autowired
    MDMMeterDataRepository meterDataRepository;

    @GetMapping("/mdm/{deviceid}")
    public ResponseEntity<?> getMeterDataByMeterCode(@PathVariable(value="deviceid") String deviceId)
    {

       // List<MDMMeterData > mdm = meterDataRepository.findByDeviceId(deviceId);

        List<MDMMeterData > mdm = meterDataRepository.findByDeviceIdOrderByTimeOfReadingAsc(deviceId);

        return ResponseEntity.ok(mdm);
    }
}
