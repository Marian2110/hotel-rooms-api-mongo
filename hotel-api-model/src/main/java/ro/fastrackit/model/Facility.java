package ro.fastrackit.model;

import lombok.Builder;

@Builder
public record Facility(
        String id,
        boolean tv,
        boolean doubleBed
) {
}
