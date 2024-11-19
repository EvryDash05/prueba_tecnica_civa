package com.example.back_prueba_tecnica_civa.entity;

import com.example.back_prueba_tecnica_civa.commons.constants.DatabaseConstants;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DatabaseConstants.ROLE_TABLE)
public class RoleEntity {

    @Id
    @Column(name = DatabaseConstants.ROLE_ID, nullable = false, length = 6)
    private String roleId;

    @Column(name = DatabaseConstants.ROLE_NAME, nullable = false, length = 30, unique = true)
    private String roleName;

}
