package ro.fasttrackit.hotelserver.repository;

import org.springframework.data.jpa.domain.Specification;
import ro.fasttrackit.hotelserver.model.entity.FacilityEntity;
import ro.fasttrackit.hotelserver.model.entity.HotelEntity;
import ro.fasttrackit.hotelserver.model.entity.RoomEntity;
import ro.fasttrackit.hotelserver.model.filter.RoomFilter;

import javax.persistence.criteria.*;
import java.util.Collection;

public class RoomSpecification {
    private RoomSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<RoomEntity> buildSpecification(RoomFilter criteria) {
        return (root, query, cb) -> {
            if (criteria.number() != null) {
                query.where(cb.equal(root.get("number"), criteria.number()));
            }
            if (criteria.floor() != null) {
                query.where(cb.equal(root.get("floor"), criteria.floor()));
            }
            if (criteria.hotel() != null) {
                Join<RoomEntity, HotelEntity> hotelEntityJoin = root.join("hotel");
                query.where(cb.equal(hotelEntityJoin.get("name"), criteria.hotel()));
            }
            if (criteria.tv() != null && criteria.tv()) {
                queryFacility(root, query, cb, "TV");
            }
            if (criteria.doubleBed() != null && criteria.doubleBed()) {
                queryFacility(root, query, cb, "Double bed");
            }
            return query.getRestriction();
        };
    }

    private static void queryFacility(Root<RoomEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb, String facilityName) {
        query.distinct(true);
        Subquery<FacilityEntity> subQuery = query.subquery(FacilityEntity.class);
        Root<FacilityEntity> facilityEntityRoot = subQuery.from(FacilityEntity.class);
        Expression<Collection<RoomEntity>> rooms = facilityEntityRoot.get("rooms");
        subQuery.select(facilityEntityRoot);
        subQuery.where(cb.equal(facilityEntityRoot.get("name"), facilityName), cb.isMember(root, rooms));
        query.where(cb.exists(subQuery));
    }
}
