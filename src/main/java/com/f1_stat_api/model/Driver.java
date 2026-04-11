package com.f1_stat_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="drivers")
public class Driver {
    @Id
    private String driverId;
    private String givenName;
    private String familyName;
    private String nationality;
    private String dateOfBirth;
    private String permanentNumber;
}
