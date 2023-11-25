package com.ghuddy.backendapp;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tour_package_types")
public class TourPackageType {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "deleted")
    private Boolean deleted;

    @Size(max = 255)
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "version")
    private Integer version;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Size(max = 255)
    @NotNull
    @Column(name = "package_type_name", nullable = false)
    private String packageTypeName;

    @NotNull
    @Column(name = "description", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @NotNull
    @Column(name = "suitable_for", nullable = false)
    private Integer suitableFor;

}