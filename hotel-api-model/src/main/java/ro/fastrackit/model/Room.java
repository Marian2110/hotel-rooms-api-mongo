package ro.fastrackit.model;

import lombok.Builder;

@Builder
public record Room(String id, Integer number, Integer floor, String hotelId, Facility facility) {
}

