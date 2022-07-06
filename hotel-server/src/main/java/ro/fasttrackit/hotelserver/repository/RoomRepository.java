package ro.fasttrackit.hotelserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.hotelserver.model.entity.RoomEntity;

public interface RoomRepository extends MongoRepository<RoomEntity, String> {
}
