package com.f1_stat_api.repositary;

import com.f1_stat_api.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, String> {

   List<Favourite> findByUserId(Long userId);
    @Transactional
    void deleteByUserIdAndDriverId(Long userId,String id);
    boolean existsByUserIdAndDriverId(Long userId,String id);

}
