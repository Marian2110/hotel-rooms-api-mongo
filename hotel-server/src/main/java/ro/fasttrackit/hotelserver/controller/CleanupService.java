package ro.fasttrackit.hotelserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.fasttrackit.hotelserver.model.entity.CleanupEntity;

@Service
@RequiredArgsConstructor
public class CleanupService {
    private final CleanupRepository cleanupRepository;

    public Page<CleanupEntity> getCleanupsForRoom(Long id, Pageable pageable) {
        return cleanupRepository.findByRoomId(id, pageable);
    }
}
