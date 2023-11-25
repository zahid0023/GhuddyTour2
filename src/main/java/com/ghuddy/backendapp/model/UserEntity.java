package com.ghuddy.backendapp.model;


import lombok.*;

import javax.persistence.*;

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

}
