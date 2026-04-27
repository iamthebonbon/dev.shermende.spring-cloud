package dev.shermende.game.service.impl;

import dev.shermende.game.resource.BalabobaRequestResource;
import dev.shermende.game.service.TextProvider;
import dev.shermende.game.service.feign.BalabobaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalabobaTextProvider implements TextProvider<String, String> {

    private final BalabobaService balabobaService;

    @Override
    public String generate(
            @NotNull String query
    ) {
        return String.format("%s. %s", query,
                balabobaService.introspect(BalabobaRequestResource.builder().query(query)
                        .build()).orElseThrow(IllegalArgumentException::new).getText());
    }

}
