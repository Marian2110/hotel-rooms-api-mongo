package ro.fasttrackit.hotelserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.fasttrackit.hotelserver.model.entity.RoomEntity;

public interface RoomRepository extends
        JpaRepository<RoomEntity, Long>,
        JpaSpecificationExecutor<RoomEntity> {
}
