package ir.ofoghkish.objecttracking.entity;

import com.sun.istack.NotNull;
import ir.ofoghkish.objecttracking.entity.enumeration.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TBL_COORDINATION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coordination extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "N_LATITUDE")
    private BigDecimal latitude;

    @NotNull
    @Column(name = "N_LONGITUDE")
    private BigDecimal longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_CAR_ID", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "COORDINATION2CAR"))
    private Car car;

    @Column(name = "F_CAR_ID")
    private Long carId;

    @Column(name = "N_CAR_TYPE")
    private CarType carType;

}
