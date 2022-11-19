package org.agafvic.chargepoints.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByNumber(String number);

}
