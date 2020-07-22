package ir.ofoghkish.objecttracking.entity;

import ir.ofoghkish.objecttracking.entity.enumeration.CarType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TBL_COORDINATION")
@RequiredArgsConstructor
@Getter
@Setter
public class Coordination extends Auditable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "N_LATITUDE")
    private BigDecimal latitude;
    @Column(name = "N_LONGITUDE")
    private BigDecimal longitude;
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;
    @Column(name = "F_CAR_ID")
    private Long carId;
    @Column(name = "N_CAR_TYPE")
    private CarType carType;

}
