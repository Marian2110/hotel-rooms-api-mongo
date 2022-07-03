package ro.fasttrackit.hotelserver.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.hotelserver.model.entity.CleanupEntity;

public interface CleanupRepository extends JpaRepository<CleanupEntity, Long> {
    Page<CleanupEntity> findByRoomId(Long id, Pageable pageable);
}
