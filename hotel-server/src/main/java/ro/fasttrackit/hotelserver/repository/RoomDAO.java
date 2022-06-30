package ro.fasttrackit.hotelserver.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelserver.model.entity.RoomEntity;
import ro.fasttrackit.hotelserver.model.filter.RoomFilter;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomDAO {
    private final EntityManager entityManager;

    public Page<RoomEntity> findAll(RoomFilter criteria, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RoomEntity> query = builder.createQuery(RoomEntity.class);
        Specification<RoomEntity> specification = RoomSpecification.buildSpecification(criteria);
        Root<RoomEntity> root = builder.createQuery(RoomEntity.class).from(RoomEntity.class);

        query.where(specification.toPredicate(root, query, builder));

        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize());

        List<RoomEntity> rooms = entityManager
                .createQuery(query)
                .setFirstResult((int) pageRequest.getOffset())
                .setMaxResults(pageRequest.getPageSize())
                .getResultList();

        long total = entityManager
                .createQuery(query)
                .getResultList()
                .size();

        return new PageImpl<>(rooms, pageRequest, total);
    }
}