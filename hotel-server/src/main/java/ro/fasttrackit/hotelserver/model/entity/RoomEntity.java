package ro.fasttrackit.hotelserver.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@Entity(name = "room")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Integer number;
    private Integer floor;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
    @ManyToOne
    private HotelEntity hotel;
    @ManyToMany
    @ToString.Exclude
    private List<FacilityEntity> facilities;
}

