package ro.fasttrackit.hotelserver.model.mapper;

import org.springframework.stereotype.Component;
import ro.fastrackit.model.Hotel;
import ro.fasttrackit.hotelserver.model.entity.HotelEntity;

@Component
public class HotelMapper implements ModelMapper<Hotel, HotelEntity> {

    @Override
    public Hotel toApi(HotelEntity entity) {
        return Hotel.builder()
                .id(entity.id())
                .name(entity.name())
                .address(entity.address())
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
