package com.f1_stat_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String Name;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String Password;

}
