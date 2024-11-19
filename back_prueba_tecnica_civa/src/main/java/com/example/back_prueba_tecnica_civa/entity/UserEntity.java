package com.example.back_prueba_tecnica_civa.entity;

import com.example.back_prueba_tecnica_civa.commons.constants.DatabaseConstants;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DatabaseConstants.USER_TABLE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DatabaseConstants.USER_ID, length = 6, nullable = false)
    private Integer customerId;

    @Column(name = DatabaseConstants.USERNAME, length = 50, nullable = false)
    private String username;

    @Column(name = DatabaseConstants.EMAIL, length = 100, nullable = false)
    private String email;

    @Column(name = DatabaseConstants.PASSWORD, length = 100, nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = DatabaseConstants.USER_ROLE_TABLE,
            joinColumns = @JoinColumn(name = DatabaseConstants.USER_ROLE_USER_ID),
            inverseJoinColumns = @JoinColumn(name = DatabaseConstants.USER_ROLE_ROLE_ID)
    )
    private Set<RoleEntity> roles;

}