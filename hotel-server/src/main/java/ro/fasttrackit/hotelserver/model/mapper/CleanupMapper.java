package ro.fasttrackit.hotelserver.model.mapper;

import org.springframework.stereotype.Component;
import ro.fastrackit.model.Cleanup;
import ro.fasttrackit.hotelserver.model.entity.CleanupEntity;

@Component
public class CleanupMapper implements ModelMapper<Cleanup, CleanupEntity> {

    @Override
    public Cleanup toApi(CleanupEntity entity) {
        return Cleanup.builder()
                .id(entity.id())
                .date(entity.date())
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
