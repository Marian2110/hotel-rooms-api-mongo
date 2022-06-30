package ro.fastrackit.model;

import lombok.Builder;

@Builder
public record Hotel(Long id, String name, String address) {
}
