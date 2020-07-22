package ir.ofoghkish.objecttracking.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CarDTO {

    private List<CoordinationDTO.Info> coordinations;

    @Getter
    @Setter
    public static class Info extends CarDTO {
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

    }

    @Getter
    @Setter
    public static class Update extends CarDTO {
        @NotNull
        private Long id;
    }

    @Getter
    @Setter
    public static class Delete extends CarDTO {
        @NotNull
        private Long id;
    }
}
