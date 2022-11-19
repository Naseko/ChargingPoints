package org.agafvic.chargepoints.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChargingSessionRepository extends CrudRepository<ChargingSessionEntity,Long> {
    Optional<ChargingSessionEntity> findByNumber(String number);

    @Query(value =
            "from ChargingSessionEntity t where start_time >= :startDate AND (stop_time <= :endDate OR stop_time IS NULL)")
    List<ChargingSessionEntity> findAllInRange(OffsetDateTime startDate, OffsetDateTime endDate);
}
