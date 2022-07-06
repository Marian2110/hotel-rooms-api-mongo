package ro.fasttrackit.hotelserver.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.fastrackit.model.CollectionResponse;
import ro.fastrackit.model.PageInfo;
import ro.fastrackit.model.Room;
import ro.fasttrackit.hotelserver.exception.custom.ResourceNotFoundException;
import ro.fasttrackit.hotelserver.model.entity.RoomEntity;
import ro.fasttrackit.hotelserver.model.filter.RoomFilter;
import ro.fasttrackit.hotelserver.model.mapper.RoomMapper;
import ro.fasttrackit.hotelserver.service.RoomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @GetMapping
    public CollectionResponse<Room> getAllRooms(RoomFilter criteria, Pageable pageable) {
        Page<RoomEntity> rooms = roomService.getAllRooms(criteria, pageable);
        return CollectionResponse.<Room>builder()
                .content(roomMapper.toApi(rooms.getContent()))
                .pageInfo(PageInfo.builder()
                        .currentPage(rooms.getPageable().getPageNumber())
                        .pageSize(rooms.getSize())
                        .totalElements(rooms.getNumberOfElements())
                        .totalPages(rooms.getTotalPages())
                        .build())
                .build();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable String id) {
        return roomMapper.toApi(roomService
                .getRoom(id)
                .orElseThrow(() -> getResourceNotFoundException(id)));
    }

    @PatchMapping("/{id}")
    public Room patchRoom(@PathVariable String id, @RequestBody JsonPatch room) {
        return roomMapper.toApi(roomService
                .patchRoom(id, room)
                .orElseThrow(() -> getResourceNotFoundException(id)));
    }

    @DeleteMapping("/{id}")
    public Room deleteRoom(@PathVariable String id) {
        return roomMapper.toApi(roomService
                .deleteRoom(id)
                .orElseThrow(() -> getResourceNotFoundException(id)));
    }

    private ResourceNotFoundException getResourceNotFoundException(String id) {
        return ResourceNotFoundException.forEntity(Room.class, id);
    }

}
