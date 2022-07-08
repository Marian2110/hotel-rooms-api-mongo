package ro.fasttrackit.hotelserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.hotelserver.model.entity.HotelEntity;

import java.util.Optional;

public interface HotelRepository extends MongoRepository<HotelEntity, String> {
    Optional<HotelEntity> findByName(String name);
}
