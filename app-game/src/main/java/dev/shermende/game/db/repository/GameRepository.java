package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.QGame;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface GameRepository extends QueryDslRepository<Game, Long, QGame> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QGame root) {

    }

}
