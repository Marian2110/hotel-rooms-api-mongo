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
import ro.fastrackit.model.Room;
import ro.fasttrackit.hotelserver.model.entity.RoomEntity;
import ro.fasttrackit.hotelserver.model.filter.RoomFilter;
import ro.fasttrackit.hotelserver.model.mapper.RoomMapper;
import ro.fasttrackit.hotelserver.repository.RoomRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper mapper;

    public Page<RoomEntity> getAllRooms(RoomFilter criteria, Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    public Optional<RoomEntity> getRoom(String id) {
        return roomRepository.findById(id);
    }

    public Optional<RoomEntity> patchRoom(String id, JsonPatch room) {
        return roomRepository
                .findById(id)
                .map(roomEntity -> getRoomEntity(room, roomEntity));
    }

    private RoomEntity getRoomEntity(JsonPatch room, RoomEntity roomEntity) {
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode jsonNode = jsonMapper
                .convertValue(
                        mapper.toApi(roomEntity),
                        JsonNode.class
                );
        try {
            JsonNode patchedJson = room.apply(jsonNode);
            return mapper.toEntity(jsonMapper.treeToValue(patchedJson, Room.class));
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid patch + " + e.getMessage());
        }
    }

    public Optional<RoomEntity> deleteRoom(String id) {
        return roomRepository.findById(id).map(roomEntity -> {
            roomRepository.delete(roomEntity);
            return roomEntity;
        });
    }
}

