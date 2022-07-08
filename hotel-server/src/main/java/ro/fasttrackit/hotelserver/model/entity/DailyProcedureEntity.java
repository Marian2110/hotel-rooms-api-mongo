package ro.fasttrackit.hotelserver.model.entity;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Document(collection = "daily_procedures")
public record DailyProcedureEntity(
        @Id
        String id,
        LocalDateTime date,
        String cleanupId,
        @CreatedDate
        LocalDateTime createdOn,
        @LastModifiedDate
        LocalDateTime updatedOn
) {
}


