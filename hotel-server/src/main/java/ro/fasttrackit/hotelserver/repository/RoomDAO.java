package ro.fasttrackit.hotelserver.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelserver.model.entity.HotelEntity;
import ro.fasttrackit.hotelserver.model.entity.RoomEntity;
import ro.fasttrackit.hotelserver.model.filter.RoomFilter;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.springframework.data.support.PageableExecutionUtils.getPage;

@Repository
@RequiredArgsConstructor
public class RoomDAO {

    private final MongoTemplate mongoTemplate;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public Page<RoomEntity> findBy(RoomFilter criteria, Pageable pageable) {
        Criteria criteriaQuery = new Criteria();
        ofNullable(criteria.number()).ifPresent(number -> criteriaQuery.and("number").is(number));
        ofNullable(criteria.floor()).ifPresent(floor -> criteriaQuery.and("floor").is(floor));
        ofNullable(criteria.hotel()).ifPresent(hotel -> criteriaQuery.and("hotelId").is(
                hotelRepository.findByName(hotel).map(HotelEntity::id).orElse(null)
        ));
        ofNullable(criteria.tv()).ifPresent(tv -> criteriaQuery.and("facility.tv").is(tv));
        ofNullable(criteria.doubleBed()).ifPresent(doubleBed -> criteriaQuery.and("facility.doubleBed").is(doubleBed));

        Query query = new Query(criteriaQuery).with(pageable);

        List<RoomEntity> rooms = mongoTemplate.find(query, RoomEntity.class);

        return getPage(rooms, pageable, () -> mongoTemplate.count(Query.query(criteriaQuery), RoomEntity.class));
    }
}
