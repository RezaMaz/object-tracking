package ir.ofoghkish.objecttracking.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import ir.ofoghkish.objecttracking.entity.enumeration.CarType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CoordinationDTO {

    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long carId;
    private CarType carType;
    private CarDTO.Info car;

    @Getter
    @Setter
    public static class Info extends CoordinationDTO {
        private Long id;
        private Date createdDate;
        private String createdBy;
        private Date modifiedDate;
        private String modifiedBy;
        private Integer version;
    }

    @Getter
    @Setter
    public static class Create extends CoordinationDTO {

    }

    @Getter
    @Setter
    public static class Update extends CoordinationDTO {
        @NotNull
        private Long id;
    }

    @Getter
    @Setter
    public static class Delete extends CoordinationDTO {
        @NotNull
        private Long id;
    }
}
