package dev.shermende.game.db.entity;

import dev.shermende.lib.dal.db.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Route extends BaseEntity<Long> {
    private static final long serialVersionUID = 1425335737055233411L;

    @NotNull
    private Long userId;

    @NotNull
    private Long gameId;

    @NotNull
    private Long sourcePointId;

    @NotNull
    private Long reasonId;

    @NotNull
    private Long targetPointId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Route gameMap = (Route) o;
        return Objects.equals(userId, gameMap.userId) && Objects.equals(gameId, gameMap.gameId) && Objects.equals(sourcePointId, gameMap.sourcePointId) && Objects.equals(reasonId, gameMap.reasonId) && Objects.equals(targetPointId, gameMap.targetPointId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, gameId, sourcePointId, reasonId, targetPointId);
    }
}
