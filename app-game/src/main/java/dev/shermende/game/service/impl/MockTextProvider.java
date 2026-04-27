package dev.shermende.game.service.impl;

import dev.shermende.game.service.TextProvider;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Primary
@Service
@Profile({"local"})
public class MockTextProvider implements TextProvider<String, String> {

    @Override
    public String generate(
            @NotNull String query
    ) {
        return String.format("%s. Mock generated text for: %s", query, query);
    }

}
