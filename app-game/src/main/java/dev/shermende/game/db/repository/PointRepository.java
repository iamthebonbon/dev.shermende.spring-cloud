package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.Point;
import dev.shermende.game.db.entity.QPoint;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface PointRepository extends QueryDslRepository<Point, Long, QPoint> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QPoint root) {

    }

}
