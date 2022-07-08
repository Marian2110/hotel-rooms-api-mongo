package ro.fasttrackit.hotelserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.hotelserver.model.entity.CleanupEntity;

import java.util.Optional;

public interface CleanupRepository extends MongoRepository<CleanupEntity, String> {
    Page<CleanupEntity> findByRoomId(String id, Pageable pageable);

    Optional<CleanupEntity> findByIdAndRoomId(String id, String roomId);
}
