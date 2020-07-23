package ir.ofoghkish.objecttracking.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import ir.ofoghkish.objecttracking.entity.enumeration.CarType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CarDTO {

    private CarType type;

    @Getter
    @Setter
    public static class Info extends CarDTO {

        private List<CoordinationDTO.Info> coordinations;

        private Long id;
        private Date createdDate;
        private String createdBy;
        private Date modifiedDate;
        private String modifiedBy;
        private Integer version;
    }

    @Getter
    @Setter
    public static class Create extends CarDTO {
        private List<CoordinationDTO.Create> coordinations;
    }

    @Getter
    @Setter
    public static class Update extends CarDTO {
        @NotNull
        private Long id;

        private List<CoordinationDTO.Create> coordinations;
    }

    @Getter
    @Setter
    public static class Delete extends CarDTO {
        @NotNull
        private Long id;
    }
}
