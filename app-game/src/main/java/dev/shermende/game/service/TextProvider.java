package dev.shermende.game.service;

import org.jetbrains.annotations.NotNull;

public interface TextProvider<I, O> {
    O generate(
            @NotNull I i
    );
}
