package ro.fasttrackit.hotelserver.model.mapper;

import lombok.RequiredArgsConstructor;
import ro.fastrackit.model.Room;
import ro.fasttrackit.hotelserver.model.entity.RoomEntity;

@RequiredArgsConstructor
public class RoomMapper implements ModelMapper<Room, RoomEntity> {
    private final HotelMapper hotelMapper;

    @Override
    public Room toApi(RoomEntity roomEntity) {
        return Room.builder()
                .id(roomEntity.getId())
                .number(roomEntity.getNumber())
                .floor(roomEntity.getFloor())
                .hotel(hotelMapper.toApi(roomEntity.getHotel()))
                .build();
    }

    @Override
    public RoomEntity toEntity(Room room) {
        return RoomEntity.builder()
                .id(room.id())
                .number(room.number())
                .floor(room.floor())
                .hotel(hotelMapper.toEntity(room.hotel()))
                .build();
    }

}
