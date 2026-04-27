package dev.shermende.game.db.entity;

import dev.shermende.lib.dal.db.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Point extends BaseEntity<Long> {

    private Long gameId;

    private Long pointId;

    private String intro;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Point that = (Point) o;
        return Objects.equals(gameId, that.gameId) && Objects.equals(pointId, that.pointId) && Objects.equals(intro, that.intro) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gameId, pointId, intro, description);
    }
}
