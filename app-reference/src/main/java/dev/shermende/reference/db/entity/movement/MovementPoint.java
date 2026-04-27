package dev.shermende.reference.db.entity.movement;

import dev.shermende.lib.dal.db.entity.TimedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovementPoint extends TimedEntity<Long> {

    private static final long serialVersionUID = -5368034288441153128L;

    @Column(name = "scenario_id", insertable = false, updatable = false)
    private Long scenarioId;

    private String intro;

}
