package com.example.back_prueba_tecnica_civa.entity;

import com.example.back_prueba_tecnica_civa.commons.constants.DatabaseConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DatabaseConstants.AUTHORITIES_TABLE)
public class AuthorityEntity {

    @Id
    @Column(name = DatabaseConstants.AUTHORITIES_ID, nullable = false, length = 6)
    private String authorityId;

    @Column(name = DatabaseConstants.AUTHORITIES_NAME, nullable = false, length = 50, unique = true)
    private String name;

}
