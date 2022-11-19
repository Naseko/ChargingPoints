package org.agafvic.chargepoints.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ChargingPointRepository extends CrudRepository<ChargingPointEntity, String> {
    Optional<ChargingPointEntity> findByUsn(String usn);
}