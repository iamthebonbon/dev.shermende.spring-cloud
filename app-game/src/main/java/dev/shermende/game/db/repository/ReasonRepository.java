package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.QReason;
import dev.shermende.game.db.entity.Reason;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface ReasonRepository extends QueryDslRepository<Reason, Long, QReason> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QReason root) {

    }

}
