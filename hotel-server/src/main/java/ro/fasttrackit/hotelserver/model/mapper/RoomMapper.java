package ro.fasttrackit.hotelserver.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fastrackit.model.Room;
import ro.fasttrackit.hotelserver.model.entity.RoomEntity;

@Component
@RequiredArgsConstructor
public class RoomMapper implements ModelMapper<Room, RoomEntity> {
    private final FacilityMapper facilityMapper;

    @Override
    public Room toApi(RoomEntity roomEntity) {
        return Room.builder()
                .id(roomEntity.id())
                .number(roomEntity.number())
                .floor(roomEntity.floor())
                .hotelId(roomEntity.hotelId())
                .facility(facilityMapper.toApi(roomEntity.facility()))
                .build();
    }

    @Override
    public RoomEntity toEntity(Room room) {
        return RoomEntity.builder()
                .id(room.id())
                .number(room.number())
                .floor(room.floor())
                .hotelId(room.hotelId())
                .facility(facilityMapper.toEntity(room.facility()))
                .build();
    }

}
