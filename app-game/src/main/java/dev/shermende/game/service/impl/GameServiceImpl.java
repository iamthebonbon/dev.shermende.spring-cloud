package dev.shermende.game.service.impl;

import com.querydsl.core.types.Predicate;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.QGame;
import dev.shermende.game.db.repository.GameRepository;
import dev.shermende.game.processor.gamecreateprocessor.GameCreateProcessor;
import dev.shermende.game.processor.gamecreateprocessor.GameCreateProcessorCtx;
import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.game.service.GameService;
import dev.shermende.lib.dal.service.AbstractCrudService;
import dev.shermende.lib.security.model.impl.PrincipalUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameServiceImpl extends AbstractCrudService<Game, Long, QGame> implements GameService {

    private final GameCreateProcessor gameCreateProcessor;

    public GameServiceImpl(
            GameRepository repository,
            GameCreateProcessor gameCreateProcessor
    ) {
        super(repository);
        this.gameCreateProcessor = gameCreateProcessor;
    }

    @Override
    public @NotNull Page<Game> findAll(
            @NotNull Authentication authentication,
            @Nullable Predicate predicate,
            @NotNull Pageable pageable
    ) {
        final PrincipalUser auth = (PrincipalUser) authentication.getPrincipal();
        return findAll(QGame.game.userId.eq(auth.getId()).and(predicate), pageable);
    }

    @Override
    @Transactional
    public @NotNull Game create(
            @NotNull Authentication authentication,
            @NotNull GameCreateResource resource
    ) {
        return gameCreateProcessor.execute(GameCreateProcessorCtx.builder().resource(resource).build()).getGame();
    }


}
