package org.agafvic.chargepoints.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "charging_session")
public class ChargingSessionEntity {
    @Column(name = "number")
    String number;
    @Column(name = "meter")
    double meter;
    @Column(name = "start_time")
    Instant startTime;
    @Column(name = "stop_time")
    Instant stopTime;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private VehicleEntity vehicle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "error_id", referencedColumnName = "number")
    private ErrorEntity error;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChargingSessionEntity chargingSessionEntity = (ChargingSessionEntity) o;
        return id != null && Objects.equals(id, chargingSessionEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "number = " + number + ", " +
                "meter = " + meter + ", " +
                "startTime = " + startTime + ", " +
                "stopTime = " + stopTime + ")";
    }
}
