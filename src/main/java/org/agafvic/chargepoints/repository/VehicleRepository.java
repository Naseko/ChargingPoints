package org.agafvic.chargepoints.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends CrudRepository<VehicleEntity,Long> {
    Optional<VehicleEntity> findByRegPlate(String number);

}
