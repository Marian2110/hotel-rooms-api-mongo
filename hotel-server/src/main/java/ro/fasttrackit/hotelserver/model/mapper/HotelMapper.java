package ro.fasttrackit.hotelserver.model.mapper;

import ro.fastrackit.model.Hotel;
import ro.fasttrackit.hotelserver.model.entity.HotelEntity;

public class HotelMapper implements ModelMapper<Hotel, HotelEntity> {

    @Override
    public Hotel toApi(HotelEntity entity) {
        return Hotel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .build();
    }

    @Override
    public HotelEntity toEntity(Hotel entity) {
        return HotelEntity.builder()
                .id(entity.id())
                .name(entity.name())
                .address(entity.address())
                .build();
    }
}
