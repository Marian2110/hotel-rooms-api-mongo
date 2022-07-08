package ro.fasttrackit.hotelserver.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.fastrackit.model.CollectionResponse;
import ro.fastrackit.model.PageInfo;
import ro.fastrackit.model.Review;
import ro.fasttrackit.hotelserver.exception.custom.ResourceNotFoundException;
import ro.fasttrackit.hotelserver.model.entity.ReviewEntity;
import ro.fasttrackit.hotelserver.model.mapper.ReviewMapper;
import ro.fasttrackit.hotelserver.service.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms/{roomId}/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping
    public CollectionResponse<Review> getReviewsForRoom(@PathVariable String roomId, Pageable pageable) {
        Page<ReviewEntity> reviews = reviewService.getReviewsForRoom(roomId, pageable);
        return CollectionResponse.<Review>builder()
                .content(reviewMapper.toApi(reviews.getContent()))
                .pageInfo(PageInfo.builder()
                        .currentPage(reviews.getPageable().getPageNumber())
                        .pageSize(reviews.getSize())
                        .totalElements(reviews.getNumberOfElements())
                        .totalPages(reviews.getTotalPages())
                        .build())
                .build();
    }

    @PatchMapping("/{id}")
    public Review patchReview(@PathVariable String id, @RequestBody JsonPatch review, @PathVariable String roomId) {
        return reviewMapper.toApi(reviewService
                .patchReview(id, roomId, review)
                .orElseThrow(() -> getResourceNotFoundException(id)));
    }

    @PostMapping
    public Review createReview(@RequestBody Review review, @PathVariable String roomId) {
        return reviewMapper.toApi(reviewService.createReview(roomId, reviewMapper.toEntity(review)));
    }

    @DeleteMapping("/{id}")
    public Review deleteReview(@PathVariable String id, @PathVariable String roomId) {
        return reviewMapper.toApi(reviewService
                .deleteReview(id, roomId)
                .orElseThrow(() -> getResourceNotFoundException(id)));
    }

    private ResourceNotFoundException getResourceNotFoundException(String id) {
        return ResourceNotFoundException.forEntity(Review.class, id);
    }

}
