package io.upepo.huizhongmdmimport.repository;

import io.upepo.huizhongmdmimport.model.MDMMeterData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MDMMeterDataRepository extends JpaRepository<MDMMeterData, Long> {
    public List<MDMMeterData> findByDeviceId(String deviceId);

    public  List<MDMMeterData>findByDeviceIdOrderByTimeOfReadingAsc(String deviceId);
}
