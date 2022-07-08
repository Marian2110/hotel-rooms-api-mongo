package ro.fasttrackit.hotelserver.model.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "facilities")
public record FacilityEntity(
        @Id
        String id,
        boolean tv,
        boolean doubleBed
) {
}
