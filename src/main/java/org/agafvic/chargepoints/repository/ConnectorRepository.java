package org.agafvic.chargepoints.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectorRepository extends CrudRepository<ConnectorEntity, String> {
}
