package ir.ofoghkish.objecttracking.entity;

import ir.ofoghkish.objecttracking.entity.enumeration.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_CAR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "N_CAR_TYPE")
    private CarType type;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Coordination> coordinations;

}
