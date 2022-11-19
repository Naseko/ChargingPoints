package org.agafvic.chargepoints.repository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Table(name= "Charging_Points")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargingPointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "usn", nullable = false, unique = true)
    private String usn;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "chargingPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ConnectorEntity> connectors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private CustomerEntity customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChargingPointEntity that = (ChargingPointEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "usn = " + usn + ", " +
                "name = " + name + ")";
    }
}
