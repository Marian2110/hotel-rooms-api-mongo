package ro.fasttrackit.hotelserver.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@Entity(name = "cleanup")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CleanupEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalDateTime date;
    @ManyToMany
    @ToString.Exclude
    private List<DailyProcedureEntity> procedures;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
