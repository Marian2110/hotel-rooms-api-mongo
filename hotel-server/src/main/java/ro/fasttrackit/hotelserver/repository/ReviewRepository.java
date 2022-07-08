package ro.fasttrackit.hotelserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.hotelserver.model.entity.ReviewEntity;

import java.util.Optional;

public interface ReviewRepository extends MongoRepository<ReviewEntity, String> {
    Page<ReviewEntity> findByRoomId(String roomId, Pageable pageable);

    Optional<ReviewEntity> findByRoomIdAndId(String roomId, String id);
}
