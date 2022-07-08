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
import ro.fastrackit.model.Review;
import ro.fasttrackit.hotelserver.model.entity.ReviewEntity;
import ro.fasttrackit.hotelserver.model.mapper.ReviewMapper;
import ro.fasttrackit.hotelserver.repository.ReviewRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper mapper;

    public Page<ReviewEntity> getReviewsForRoom(String roomId, Pageable pageable) {
        return reviewRepository.findByRoomId(roomId, pageable);
    }

    public Optional<ReviewEntity> patchReview(String id, String roomId, JsonPatch review) {
        return reviewRepository
                .findByRoomIdAndId(roomId, id)
                .map(reviewEntity -> getReviewEntity(review, reviewEntity));
    }

    private ReviewEntity getReviewEntity(JsonPatch review, ReviewEntity reviewEntity) {
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode jsonNode = jsonMapper
                .convertValue(
                        mapper.toApi(reviewEntity),
                        JsonNode.class
                );
        try {
            JsonNode patchedJson = review.apply(jsonNode);
            return mapper.toEntity(jsonMapper.treeToValue(patchedJson, Review.class));
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid patch + " + e.getMessage());
        }
    }

    public ReviewEntity createReview(String roomId, ReviewEntity toEntity) {
        return reviewRepository.save(toEntity.withRoomId(roomId));
    }

    public Optional<ReviewEntity> deleteReview(String id, String roomId) {
        return reviewRepository.findByRoomIdAndId(roomId, id)
                .map(reviewEntity -> {
                    reviewRepository.delete(reviewEntity);
                    return reviewEntity;
                });
    }
}
