package com.f1_stat_api.Controller;

import com.f1_stat_api.Service.DriverService;
import com.f1_stat_api.model.Driver;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
//@CrossOrigin(origins = "http://localhost:5173")

public class DriverController {

    private final DriverService driverService;
     public DriverController(DriverService driverService) {
         this.driverService = driverService;
     }

     @GetMapping
    public List<Driver> findAllDriver(){
         return driverService.getAll();
     }


}
