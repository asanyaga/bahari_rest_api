package io.upepo.huizhongmdmimport.repository;

import io.upepo.huizhongmdmimport.model.MDMMeterData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MDMMeterDataRepository extends JpaRepository<MDMMeterData, Long> {
    public List<MDMMeterData> findByDeviceId(String deviceId);

    public  List<MDMMeterData>findByDeviceIdOrderByTimeOfReadingAsc(String deviceId);

    @Query("FROM MDMMeterData WHERE deviceId =?1 AND timeOfReading > ?2 ORDER BY timeOfReading ASC")
    public List<MDMMeterData> findReadingsSinceLastReportDate(String deviceId, Date lastReportingDate);

    @Query("SELECT MAX(timeOfReading) FROM MDMMeterData WHERE deviceId =?1")
    Date findLatestReadingDate(String deviceId);
}
