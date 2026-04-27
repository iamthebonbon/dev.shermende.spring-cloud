package dev.shermende.game.validator;

import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.reference.lib.api.MovementScenarioApiService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GameCreateResourceValidator extends AbstractHttpValidator {

    private final MovementScenarioApiService service;

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return GameCreateResource.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(@NotNull Object o, @NotNull Errors errors) {
        final GameCreateResource resource = (GameCreateResource) o;
        if (!Optional.ofNullable(resource.getScenarioId()).filter(id -> Objects.nonNull(service.findById(id))).isPresent())
            errors.rejectValue("scenarioId", "not-found", new Object[]{}, "not-found");
    }

}
