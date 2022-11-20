package org.agafvic.chargepoints.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatternRepository extends CrudRepository<CustomerEntity, Long> {
    @Query(value = "from PatternEntity t")
    List<PatternEntity> selectAll();
}