package com.f1_stat_api.Controller;

import com.f1_stat_api.Security.JwtUtil;
import com.f1_stat_api.model.Driver;
import com.f1_stat_api.model.Favourite;
import com.f1_stat_api.repositary.DriverRepository;
import com.f1_stat_api.repositary.FavouriteRepository;
import com.f1_stat_api.repositary.UserRepository;
import jakarta.persistence.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver/favourite")
//@CrossOrigin(origins = "http://localhost:5173")
public class FavouriteController {

    private final FavouriteRepository favouriteRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public FavouriteController(FavouriteRepository favouriteRepository,UserRepository userRepository,JwtUtil jwtUtil) {
        this.favouriteRepository = favouriteRepository;
        this .userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }
    private Long getUserIdFromToken(String authHeader) {
        String token = authHeader.substring(7); // remove "Bearer "
        String email = jwtUtil.extractEmail(token);;
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
    }

    @GetMapping
    public ResponseEntity<?> getFavourites(@RequestHeader("Authorization") String authHeader) {
        Long userId=getUserIdFromToken(authHeader);

        return ResponseEntity.ok(favouriteRepository.findByUserId(userId));
    }

    @PostMapping("/{driverId}")
    public ResponseEntity<?> addFavourite(@PathVariable String driverId,@RequestHeader("Authorization") String authHeader) {
        Long userId=getUserIdFromToken(authHeader);
        if (favouriteRepository.existsByUserIdAndDriverId(userId, driverId)) {
            return ResponseEntity.ok("Already favourited");
        }

        Favourite favourite = new Favourite();
        favourite.setDriverId(driverId);
        favourite.setUserId(userId);
        favouriteRepository.save(favourite);
        return ResponseEntity.ok("Added to favourites");
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<?> deleteFavourite(@PathVariable String driverId,@RequestHeader("Authorization") String authHeader) {
        Long userId = getUserIdFromToken(authHeader);
        favouriteRepository.deleteByUserIdAndDriverId(userId, driverId);
        return ResponseEntity.ok("Removed from favourites");
    }
}
