package ro.fasttrackit.hotelserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fastrackit.model.Cleanup;
import ro.fastrackit.model.CollectionResponse;
import ro.fastrackit.model.PageInfo;
import ro.fasttrackit.hotelserver.model.entity.CleanupEntity;
import ro.fasttrackit.hotelserver.model.mapper.CleanupMapper;
import ro.fasttrackit.hotelserver.service.CleanupService;

@RestController
@RequestMapping("/cleanups")
@RequiredArgsConstructor
public class CleanupController {
    private final CleanupService cleanupService;
    private final CleanupMapper cleanupMapper;

    @GetMapping("/rooms/{id}")
    public CollectionResponse<Cleanup> getCleanupsForRoom(@PathVariable Long id, Pageable pageable) {
        Page<CleanupEntity> cleanups = cleanupService.getCleanupsForRoom(id, pageable);
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
}
