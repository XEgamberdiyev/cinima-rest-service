package uz.pdp.cinemarestservice.model.abcClass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbsEntity {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

//    @OrderBy
//    @CreationTimestamp
//    @Column(nullable = false, updatable = false)
//    private Timestamp createdAt;
//
//    @UpdateTimestamp
//    @Column(nullable = false)
//    private Timestamp updatedAt;
//
//    @CreatedBy
//    @Column(name = "created_by_id")
//    private UUID createdBy;
//
//    @LastModifiedBy
//    @Column(name = "updated_by_id")
//    private UUID updatedBy;

}