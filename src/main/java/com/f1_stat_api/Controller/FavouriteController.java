package com.f1_stat_api.Controller;

import com.f1_stat_api.model.Driver;
import com.f1_stat_api.model.Favourite;
import com.f1_stat_api.repositary.DriverRepository;
import com.f1_stat_api.repositary.FavouriteRepository;
import jakarta.persistence.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver/favourite")
@CrossOrigin(origins = "http://localhost:5173")
public class FavouriteController {

    private final FavouriteRepository favouriteRepository;

    public FavouriteController(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @GetMapping
    public ResponseEntity<?> getFavourites() {
        return ResponseEntity.ok(favouriteRepository.findAll());
    }

    @PostMapping("/{driverId}")
    public ResponseEntity<?> addFavourite(@PathVariable String driverId) {
        Favourite favourite = new Favourite();
        favourite.setDriverId(driverId);
        favouriteRepository.save(favourite);
        return ResponseEntity.ok("Added to favourites");
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<?> deleteFavourite(@PathVariable String driverId) {
        favouriteRepository.deleteByDriverId(driverId);
        return ResponseEntity.ok("Removed from favourites");
    }
}
