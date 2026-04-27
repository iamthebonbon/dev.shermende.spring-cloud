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
public class Game extends BaseEntity<Long> {
    private static final long serialVersionUID = 1425335737055233411L;

    @NotNull
    private Long userId;

    @NotNull
    private Long scenarioId;

    @NotNull
    private Long reasonId;

    @NotNull
    private Long pointId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Game game = (Game) o;
        return Objects.equals(userId, game.userId) && Objects.equals(scenarioId, game.scenarioId) && Objects.equals(reasonId, game.reasonId) && Objects.equals(pointId, game.pointId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, scenarioId, reasonId, pointId);
    }
}
