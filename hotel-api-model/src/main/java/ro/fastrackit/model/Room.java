package ro.fastrackit.model;

import lombok.Builder;

@Builder
public record Room(Long id, Integer number, Integer floor, Hotel hotel) {
}

