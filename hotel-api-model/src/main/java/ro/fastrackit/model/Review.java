package ro.fastrackit.model;

import lombok.Builder;

@Builder
public record Review(
        String id,
        Integer rating,
        String message,
        String touristName,
        String roomId
) {
}
