package io.upepo.huizhongmdmimport.controller;
import io.upepo.baharirestapi.model.Meter;
import io.upepo.baharirestapi.model.WaterFlowReading;
import io.upepo.baharirestapi.repository.MeterRepository;
import io.upepo.baharirestapi.repository.MeterReadingTrackerRepository;
import io.upepo.baharirestapi.model.MeterReadingTracker;

import io.upepo.baharirestapi.repository.WaterFlowReadingRepository;
import io.upepo.huizhongmdmimport.model.MDMMeterData;
import io.upepo.huizhongmdmimport.repository.MDMMeterDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class MDMMeterDataController {

    Logger logger = LoggerFactory.getLogger(MDMMeterDataController.class);

    @Autowired
    MDMMeterDataRepository meterDataRepository;

    @Autowired
    MeterRepository meterRepository;

    @Autowired
    MeterReadingTrackerRepository meterReadingTrackerRepository;

    @Autowired
    WaterFlowReadingRepository waterFlowReadingRepository;

    @GetMapping("/mdm/{deviceid}")
    public ResponseEntity<?> getMeterDataByMeterCode(@PathVariable(value="deviceid") String deviceId)
    {

       // List<MDMMeterData > mdm = meterDataRepository.findByDeviceId(deviceId);

        List<MDMMeterData > mdm = meterDataRepository.findByDeviceIdOrderByTimeOfReadingAsc(deviceId);

        return ResponseEntity.ok(mdm);
    }

    @PostMapping("/mdm/export")
    public ResponseEntity<?> exportMeterReadings()
    {

        // List<MDMMeterData > mdm = meterDataRepository.findByDeviceId(deviceId);


        List<WaterFlowReading> meterReadings = new ArrayList<>();

        List<Meter> meters = meterRepository.findAll();

        for(Meter meter: meters)
        {

            List<MDMMeterData> mdmDataList = new ArrayList<>();

            MeterReadingTracker tracker = meterReadingTrackerRepository.findByMeterId(meter.getId()).orElseGet(() -> new MeterReadingTracker());

            if(tracker.getId()==null)
            {
                mdmDataList = meterDataRepository.findByDeviceIdOrderByTimeOfReadingAsc(meter.getDeviceNumber());

                tracker.setMeterId(meter.getId());
            }
            else
            {
                mdmDataList = meterDataRepository.findReadingsSinceLastReadingDate(meter.getDeviceNumber(),tracker.getLastReadingDate());
            }

            for(MDMMeterData mdmData: mdmDataList )
            {
                WaterFlowReading flowReading = new WaterFlowReading();
                flowReading.setDeviceId(meter.getDeviceNumber());
                flowReading.setMeterId(meter.getId());
                flowReading.setIntervalFlow(mdmData.getIntervalFlow());
                flowReading.setReadingDate(mdmData.getTimeOfReading());
                flowReading.setReportingDate(new Date());
                meterReadings.add(flowReading);
            }

            waterFlowReadingRepository.saveAll(meterReadings);

            if(mdmDataList.size()>0) {
                Date lastReadingDate = meterDataRepository.findLatestReadingDate(meter.getDeviceNumber());

                tracker.setLastReportDate(new Date());
                tracker.setLastReadingDate(lastReadingDate);

                meterReadingTrackerRepository.save(tracker);
            }
        }

        //List<MDMMeterData > mdm = meterDataRepository.findByDeviceIdOrderByTimeOfReadingAsc(deviceId);

        return ResponseEntity.ok("{'Status': 'Finished'}");
    }
}
