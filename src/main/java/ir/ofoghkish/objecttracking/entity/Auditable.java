package ir.ofoghkish.objecttracking.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

    @Column(name = "D_CREATED_DATE", nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "C_CREATED_BY", nullable = false, updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "D_MODIFIED_DATE")
    @LastModifiedDate
    private Date modifiedDate;

    @Column(name = "C_MODIFIED_BY")
    @LastModifiedBy
    private String modifiedBy;

    @Version
    @Column(name = "N_VERSION", nullable = false)
    private Long version;

}
