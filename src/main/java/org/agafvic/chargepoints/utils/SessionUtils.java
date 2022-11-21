package org.agafvic.chargepoints.utils;

import org.agafvic.chargepoints.repository.ChargingSessionEntity;
import org.agafvic.chargepoints.repository.RfidEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class SessionUtils {

    @NotNull
    public ChargingSessionEntity getSession(RfidEntity rfid) {
        ChargingSessionEntity session = new ChargingSessionEntity();
        session.setStartTime(Instant.now());
        session.setVehicle(rfid.getVehicle());
        session.setNumber(UUID.randomUUID().toString());
        session.setCustomer(rfid.getCustomer());
        return session;
    }
}
