package com.f1_stat_api.Service;

import com.f1_stat_api.model.Driver;
import com.f1_stat_api.repositary.DriverRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class F1SyncService implements CommandLineRunner {

    private final DriverRepository driverRepository;
    private final RestTemplate restTemplate;

    public F1SyncService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void run(String... args) {
        if (driverRepository.count() > 0) {
            System.out.println("Drivers already synced, skipping...");
            return;
        }

        System.out.println("Fetching drivers from Jolpica API...");

        String url = "https://api.jolpi.ca/ergast/f1/2026/drivers";
        Map response = restTemplate.getForObject(url, Map.class);

        Map mrData = (Map) response.get("MRData");
        Map driverTable = (Map) mrData.get("DriverTable");
        List<Map> driverList = (List<Map>) driverTable.get("Drivers");

        List<Driver> drivers = new ArrayList<Driver>();

        for (Map d : driverList) {
            Driver driver = new Driver();
            driver.setDriverId((String) d.get("driverId"));
            driver.setGivenName((String) d.get("givenName"));
            driver.setFamilyName((String) d.get("familyName"));
            driver.setNationality((String) d.get("nationality"));
            driver.setDateOfBirth((String) d.get("dateOfBirth"));
            driver.setPermanentNumber((String) d.get("permanentNumber"));
            drivers.add(driver);
        }
        driverRepository.saveAll(drivers);

        System.out.println("Synced " + driverList.size() + " drivers to database!");
    }
}
