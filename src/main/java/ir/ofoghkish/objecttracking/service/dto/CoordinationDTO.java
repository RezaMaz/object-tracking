package ir.ofoghkish.objecttracking.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
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
        private Long carId;
    }

    @Getter
    @Setter
    public static class Delete extends CoordinationDTO {
        @NotNull
        private Long id;
    }
}
