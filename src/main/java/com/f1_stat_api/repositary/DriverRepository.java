package com.f1_stat_api.repositary;
import com.f1_stat_api.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {

}
