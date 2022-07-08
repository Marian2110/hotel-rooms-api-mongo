package ro.fasttrackit.hotelserver.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.fastrackit.model.Cleanup;
import ro.fastrackit.model.CollectionResponse;
import ro.fastrackit.model.PageInfo;
import ro.fasttrackit.hotelserver.exception.custom.ResourceNotFoundException;
import ro.fasttrackit.hotelserver.model.entity.CleanupEntity;
import ro.fasttrackit.hotelserver.model.mapper.CleanupMapper;
import ro.fasttrackit.hotelserver.service.CleanupService;


@RestController
@RequestMapping("rooms/{roomId}/cleanups")
@RequiredArgsConstructor
public class CleanupController {
    private final CleanupService cleanupService;
    private final CleanupMapper cleanupMapper;

    @GetMapping
    public CollectionResponse<Cleanup> getCleanupsForRoom(@PathVariable String roomId, Pageable pageable) {
        Page<CleanupEntity> cleanups = cleanupService.getCleanupsForRoom(roomId, pageable);
        return CollectionResponse.<Cleanup>builder()
                .content(cleanupMapper.toApi(cleanups.getContent()))
                .pageInfo(PageInfo.builder()
                        .currentPage(cleanups.getPageable().getPageNumber())
                        .pageSize(cleanups.getSize())
                        .totalElements(cleanups.getNumberOfElements())
                        .totalPages(cleanups.getTotalPages())
                        .build())
                .build();
    }

    @PostMapping
    public Cleanup addCleanupForRoom(@PathVariable String roomId, @RequestBody Cleanup cleanup) {
        CleanupEntity cleanupEntity = cleanupService.addCleanupForRoom(roomId, cleanupMapper.toEntity(cleanup));
        return cleanupMapper.toApi(cleanupEntity);
    }

    @PatchMapping("{id}")
    public Cleanup patchCleanupForRoom(@PathVariable String roomId, @PathVariable String id, @RequestBody JsonPatch cleanup) {
        return cleanupService.patchCleanupForRoom(roomId, id, cleanup)
                .map(cleanupMapper::toApi)
                .orElseThrow(() -> getResourceNotFoundException(id));
    }

    @DeleteMapping("{id}")
    public Cleanup deleteCleanupForRoom(@PathVariable String roomId, @PathVariable String id) {
        return cleanupService.deleteCleanupForRoom(roomId, id)
                .map(cleanupMapper::toApi)
                .orElseThrow(() -> getResourceNotFoundException(id));
    }

    private ResourceNotFoundException getResourceNotFoundException(String id) {
        return ResourceNotFoundException.forEntity(Cleanup.class, id);
    }

}
