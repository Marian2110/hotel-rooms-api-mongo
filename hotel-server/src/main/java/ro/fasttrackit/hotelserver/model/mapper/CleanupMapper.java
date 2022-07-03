package ro.fasttrackit.hotelserver.model.mapper;

import ro.fastrackit.model.Cleanup;
import ro.fasttrackit.hotelserver.model.entity.CleanupEntity;

public class CleanupMapper implements ModelMapper<Cleanup, CleanupEntity> {

    @Override
    public Cleanup toApi(CleanupEntity entity) {
        return Cleanup.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .build();
    }

    @Override
    public CleanupEntity toEntity(Cleanup cleanup) {
        return CleanupEntity.builder()
                .id(cleanup.id())
                .date(cleanup.date())
                .build();
    }
}
