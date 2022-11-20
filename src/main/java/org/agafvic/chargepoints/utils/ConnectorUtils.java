package org.agafvic.chargepoints.utils;

import org.agafvic.chargepoints.dto.ConnectorPointBindingDto;
import org.agafvic.chargepoints.repository.ChargingPointEntity;
import org.agafvic.chargepoints.repository.ConnectorEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ConnectorUtils {

    @NotNull
    public ConnectorEntity createConnectorEntity(ConnectorPointBindingDto binding, ChargingPointEntity chargingPointEntity) {
        ConnectorEntity connectorEntity = new ConnectorEntity();
        connectorEntity.setChargingPoint(chargingPointEntity);
        connectorEntity.setNumber(binding.getConnectorUsn());
        return connectorEntity;
    }
}
