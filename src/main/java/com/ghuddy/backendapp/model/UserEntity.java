package com.ghuddy.backendapp.model;


import com.ghuddy.backendapp.tours.model.entities.booking.SalesOrderEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : anisuzzaman
 * @created : 11/1/22
 **/

//done
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesOrderEntity> salesOrderEntities = new ArrayList<>();

}
