package ro.fasttrackit.hotelserver.model.mapper;

import org.springframework.stereotype.Component;
import ro.fastrackit.model.Review;
import ro.fasttrackit.hotelserver.model.entity.ReviewEntity;

@Component
public class ReviewMapper implements ModelMapper<Review, ReviewEntity> {
    @Override
    public Review toApi(ReviewEntity entity) {
        return Review.builder()
                .id(entity.id())
                .rating(entity.rating())
                .message(entity.message())
                .touristName(entity.touristName())
                .roomId(entity.roomId())
                .build();
    }

    @Override
    public ReviewEntity toEntity(Review api) {
        return ReviewEntity.builder()
                .id(api.id())
                .rating(api.rating())
                .message(api.message())
                .touristName(api.touristName())
                .roomId(api.roomId())
                .build();
    }
}
