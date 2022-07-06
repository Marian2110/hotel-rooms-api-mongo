package ro.fasttrackit.hotelserver.model.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Document(collection = "cleanups")
public record DailyProcedureEntity(
        @Id
        String id,
        LocalDateTime date,
        RoomEntity room,
        LocalDateTime createdOn,
        LocalDateTime updatedOn
) {
}


