package ro.fasttrackit.hotelserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.fastrackit.model.Cleanup;
import ro.fasttrackit.hotelserver.model.entity.CleanupEntity;
import ro.fasttrackit.hotelserver.model.mapper.CleanupMapper;
import ro.fasttrackit.hotelserver.repository.CleanupRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CleanupService {
    private final CleanupRepository cleanupRepository;
    private final CleanupMapper mapper;

    public Page<CleanupEntity> getCleanupsForRoom(String id, Pageable pageable) {
        return cleanupRepository.findByRoomId(id, pageable);
    }

    public CleanupEntity addCleanupForRoom(String roomId, CleanupEntity toEntity) {
        return cleanupRepository.save(toEntity.withRoomId(roomId));
    }

    public Optional<CleanupEntity> patchCleanupForRoom(String roomId, String id, JsonPatch cleanup) {
        return cleanupRepository
                .findByIdAndRoomId(id, roomId)
                .map(cleanupEntity -> getCleanupEntity(cleanup, cleanupEntity));
    }

    private CleanupEntity getCleanupEntity(JsonPatch cleanup, CleanupEntity cleanupEntity) {
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode jsonNode = jsonMapper
                .convertValue(
                        mapper.toApi(cleanupEntity),
                        JsonNode.class
                );
        try {
            JsonNode patchedJson = cleanup.apply(jsonNode);
            return mapper.toEntity(jsonMapper.treeToValue(patchedJson, Cleanup.class));
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid patch + " + e.getMessage());
        }
    }

    public Optional<CleanupEntity> deleteCleanupForRoom(String roomId, String id) {
        return cleanupRepository.findByIdAndRoomId(id, roomId)
                .map(cleanupEntity -> {
                    cleanupRepository.delete(cleanupEntity);
                    return cleanupEntity;
                });
    }
}
