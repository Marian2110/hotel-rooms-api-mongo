package ro.fastrackit.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Cleanup(Long id, LocalDateTime date) {
}
