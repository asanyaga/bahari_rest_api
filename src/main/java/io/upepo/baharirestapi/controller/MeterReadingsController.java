package io.upepo.baharirestapi.controller;

import io.upepo.baharirestapi.exception.ResourceNotFoundException;
import io.upepo.baharirestapi.model.WaterFlowReading;
import io.upepo.baharirestapi.repository.WaterFlowReadingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class MeterReadingsController {

    @Autowired
    WaterFlowReadingRepository waterFlowReadingRepository;

    @PreAuthorize("hasAuthority('METER_READ')")
    @GetMapping("/meters/{id}/waterflowreadings")
    public Page<WaterFlowReading> getWaterFlowReadingByMeterId(@PathVariable(value ="id") Long meterId, @RequestParam(value="page",defaultValue = "0") int page, @RequestParam(value="size", defaultValue = "10") int size) throws ResourceNotFoundException
    {
        Pageable pageRequest = PageRequest.of(page,size);

        Page<WaterFlowReading> flowReadings = waterFlowReadingRepository.findByMeterIdOrderByReadingDateDesc(meterId,pageRequest);

        return flowReadings;

       // return ResponseEntity.ok(waterFlowReadingRepository.findByMeterIdOrderByReadingDateDesc(meterId));
    }
}
