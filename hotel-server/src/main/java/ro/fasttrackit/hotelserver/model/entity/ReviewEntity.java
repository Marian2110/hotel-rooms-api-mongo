package ro.fasttrackit.hotelserver.model.entity;

import lombok.Builder;
import lombok.With;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@With
@Document(collection = "reviews")
public record ReviewEntity(
        @Id
        String id,
        Integer rating,
        String message,
        String touristName,
        String roomId,
        @CreatedDate
        LocalDateTime createdOn,
        @LastModifiedDate
        LocalDateTime updatedOn
) {
}
