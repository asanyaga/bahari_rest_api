package io.upepo.baharirestapi.controller;
import io.upepo.baharirestapi.exception.ResourceNotFoundException;
import io.upepo.baharirestapi.model.Tariff;
import io.upepo.baharirestapi.model.TariffBand;
import io.upepo.baharirestapi.repository.TariffBandRepository;
import io.upepo.baharirestapi.repository.TariffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class TariffController {

    @Autowired
    TariffRepository tariffRepository;

    @Autowired
    TariffBandRepository tariffBandRepository;

    @PostMapping("/tariffs")
    @PreAuthorize("hasAuthority('TARIFF_CREATE')")
    public ResponseEntity<?> createTariff(@Valid @RequestBody Tariff tariff)
    {
        for(TariffBand tariffBand: tariff.getTariffBands())
        {
            tariffBand.setTariff(tariff);
        }
        return ResponseEntity.ok(tariffRepository.save(tariff));
    }

    @GetMapping("/tariffs")
    @PreAuthorize("hasAuthority('TARIFF_READ')")
    public ResponseEntity<?>getAllTariffs()
    {
        return ResponseEntity.ok(tariffRepository.findAll());
    }

    @GetMapping("/tariffs/{id}")
    @PreAuthorize("hasAuthority('TARIFF_READ')")
    public ResponseEntity<?>getTariffById(@PathVariable(value="id") Long tariffId) throws ResourceNotFoundException
    {
        Tariff tariff = tariffRepository.findById(tariffId).orElseThrow(()->new ResourceNotFoundException("Tariff not found"));

        return ResponseEntity.ok(tariff);
    }

    @PutMapping("/tariffs/{id}")
    @PreAuthorize("hasAuthority('TARIFF_UPDATE')")
    public ResponseEntity<?>updateTariff(@PathVariable(value = "id") Long tariffId, @Valid @RequestBody Tariff tariff) throws ResourceNotFoundException
    {
        Tariff tariffInDB = tariffRepository.findById(tariffId).orElseThrow(() -> new ResourceNotFoundException("Tariff not found"));

        tariffInDB.setName(tariff.getName());
        tariffInDB.setActive(tariff.isActive());

       // tariffRepository.save(tariffInDB);

        List<TariffBand> bands = new ArrayList<>();

        for(TariffBand tariffBand: tariff.getTariffBands())
        {

            TariffBand newBand = new TariffBand();
            if(newBand.getId()!=null)
            {
                newBand= tariffBandRepository.findById(tariffBand.getId()).orElseThrow(() -> new ResourceNotFoundException("Band not found"));
            }

            newBand.setTariff(tariffInDB);
            newBand.setId(tariffBand.getId());
            newBand.setAmount(tariffBand.getAmount());
            newBand.setRateType(tariffBand.getRateType());
            newBand.setLowerLimit(tariffBand.getLowerLimit());
            newBand.setUpperLimit(tariffBand.getUpperLimit());

            tariffBandRepository .save(newBand);
            bands.add(newBand);
        }

        tariffInDB.setTariffBands(bands);

        tariffRepository.save(tariffInDB);

        return ResponseEntity.ok(tariffInDB);

    }

    @DeleteMapping("/tariffs/{id}")
    @PreAuthorize("hasAuthority('TARIFF_DELETE')")
    public ResponseEntity<?>deleteTariff(@PathVariable(value = "id") Long tariffId) throws ResourceNotFoundException
    {
        Tariff tariffInDB = tariffRepository.findById(tariffId).orElseThrow(() -> new ResourceNotFoundException("Tariff not found"));
        tariffRepository.delete(tariffInDB);
        return ResponseEntity.ok("Deleted");
    }

    @DeleteMapping("/tariffs/bands/{id}")
    @PreAuthorize("hasAuthority('TARIFF_DELETE')")
    public ResponseEntity<?>deleteTariffBand(@PathVariable(value = "id") Long tariffBandId) throws ResourceNotFoundException
    {
        TariffBand tariffBandInDB = tariffBandRepository.findById(tariffBandId).orElseThrow(() -> new ResourceNotFoundException("Tariff band not found"));
        tariffBandRepository.delete(tariffBandInDB);
        return ResponseEntity.ok("Deleted");
    }

}
