package org.agafvic.chargepoints.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Vehicles")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "reg_plate", nullable = false, unique = true)
    private String regPlate;
    private String name;

    @OneToOne(mappedBy = "vehicle")
    private ChargingSessionEntity session;

    @OneToOne(mappedBy = "vehicle")
    private RfidEntity rfid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VehicleEntity vehicleEntity = (VehicleEntity) o;
        return regPlate != null && Objects.equals(regPlate, vehicleEntity.regPlate);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "id=" + id +
                ", regPlate='" + regPlate + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
