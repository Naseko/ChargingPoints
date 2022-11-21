package org.agafvic.chargepoints.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargingPointRepository extends CrudRepository<ChargingPointEntity, String> {
    Optional<ChargingPointEntity> findByUsn(String usn);
    @Query(value =
"select count(p) > 0 from ChargingPointEntity as p right outer join p.customer as c where c.id = p.customer and p.usn = :usn and c.number = :number")
    boolean existsByUsn(String usn, String number);
}