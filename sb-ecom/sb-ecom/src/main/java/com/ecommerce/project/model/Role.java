package com.ecommerce.project.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id",unique = true, nullable = false)
    private int roleId;

    @ToString.Exclude
    @Column(name = "role_name",length = 50)
    @Enumerated(EnumType.STRING)
    private AppRole roleName;

    public Role(AppRole roleName) {
        this.roleName = roleName;
    }
}
