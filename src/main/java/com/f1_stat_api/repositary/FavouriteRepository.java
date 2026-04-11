package com.f1_stat_api.repositary;

import com.f1_stat_api.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, String> {
   @Transactional
    void deleteByDriverId(String id);
    boolean existsByDriverId(String id);

}
