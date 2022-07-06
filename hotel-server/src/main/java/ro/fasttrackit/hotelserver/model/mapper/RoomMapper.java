package ro.fasttrackit.hotelserver.model.mapper;

import org.springframework.stereotype.Component;
import ro.fastrackit.model.Room;
import ro.fasttrackit.hotelserver.model.entity.RoomEntity;

@Component
public class RoomMapper implements ModelMapper<Room, RoomEntity> {

    @Override
    public Room toApi(RoomEntity roomEntity) {
        return Room.builder()
                .id(roomEntity.id())
                .number(roomEntity.number())
                .floor(roomEntity.floor())
                .build();
    }

    @Override
    public RoomEntity toEntity(Room room) {
        return RoomEntity.builder()
                .id(room.id())
                .number(room.number())
                .floor(room.floor())
                .build();
    }

}
