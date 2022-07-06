package ro.fastrackit.model;

import lombok.Builder;

@Builder
public record Hotel(String id, String name, String address) {
}
