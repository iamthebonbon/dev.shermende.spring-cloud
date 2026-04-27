package dev.shermende.game.validator;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.resource.GameMoveResource;
import dev.shermende.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GameMoveResourceValidator extends AbstractHttpValidator {

    private final GameService gameService;

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return GameMoveResource.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(@NotNull Object o, @NotNull Errors errors) {
        if (isNotExistPathVariable(ID)) throw new IllegalArgumentException();
        final GameMoveResource resource = (GameMoveResource) o;
        if (Objects.isNull(resource.getRouteId())) return;
        final Optional<Game> game = gameService.findById(Long.valueOf(getPathVariable(ID)));
    }

}
