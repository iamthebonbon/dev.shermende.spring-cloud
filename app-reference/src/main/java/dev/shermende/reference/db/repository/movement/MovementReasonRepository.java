package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementReason;
import dev.shermende.reference.db.entity.movement.QMovementReason;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;

public interface MovementReasonRepository extends QueryDslRepository<MovementReason, Long, QMovementReason> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementReason root) {

    }

    @Query("select id from MovementReason where scenarioId = :scenarioId")
    List<Long> findIdsByScenarioId(Long scenarioId);

}
