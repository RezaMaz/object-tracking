package ir.ofoghkish.objecttracking.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;
    @Column(name = "MODIFIED_DATE")
    @LastModifiedDate
    private Date modifiedDate;
    @Column(name = "CREATED_BY")
    @CreatedBy
    private String createdBy;
    @Column(name = "MODIFIED_DATE")
    @LastModifiedBy
    private String modifiedBy;
    @Column(name = "N_VERSION")
    private Long version;

}
