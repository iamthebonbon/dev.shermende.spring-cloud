package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.QRoute;
import dev.shermende.game.db.entity.Route;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface RouteRepository extends QueryDslRepository<Route, Long, QRoute> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QRoute root) {

    }

}
