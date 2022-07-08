package ro.fasttrackit.hotelserver.model.mapper;

import org.springframework.stereotype.Component;
import ro.fastrackit.model.Facility;
import ro.fasttrackit.hotelserver.model.entity.FacilityEntity;

@Component
public class FacilityMapper implements ModelMapper<Facility, FacilityEntity> {

    @Override
    public Facility toApi(FacilityEntity entity) {
        return Facility.builder()
                .id(entity.id())
                .tv(entity.tv())
                .doubleBed(entity.doubleBed())
                .build();
    }

    @Override
    public FacilityEntity toEntity(Facility facility) {
        return FacilityEntity.builder()
                .id(facility.id())
                .tv(facility.tv())
                .doubleBed(facility.doubleBed())
                .build();
    }
}
