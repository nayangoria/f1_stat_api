package com.f1_stat_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="favouriteDriver")
public class Favourite {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String driverId;
    private Long userId;
}
