package com.f1_stat_api.repositary;

import com.f1_stat_api.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, String> {
    void deleteByDriverId(String id);
    boolean existsByDriverId(String id);

}
