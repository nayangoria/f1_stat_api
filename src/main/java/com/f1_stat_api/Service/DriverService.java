package com.f1_stat_api.Service;

import com.f1_stat_api.model.Driver;
import com.f1_stat_api.repositary.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getAll(){
        return driverRepository.findAll();
    }

    public Driver saveDriver(Driver driver){
        return driverRepository.save(driver);
    }

}
