package ro.fastrackit.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Cleanup(String id, LocalDateTime date) {
}
