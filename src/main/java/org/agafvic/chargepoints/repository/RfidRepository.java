package org.agafvic.chargepoints.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RfidRepository extends CrudRepository<RfidEntity, Long> {
    Optional<RfidEntity> findByNumber(String number);

}
