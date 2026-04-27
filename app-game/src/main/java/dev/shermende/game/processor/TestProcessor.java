package dev.shermende.game.processor;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.support.spring.processor.AbstractStepProcessor;
import dev.shermende.support.spring.processor.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;

import java.util.Collection;

@Slf4j
public class TestProcessor extends AbstractStepProcessor<GameCreateResource, Game> {

    protected TestProcessor(
            BeanFactory factory
    ) {
        super(factory);
    }

    @Override
    protected Collection<Class<? extends Step<GameCreateResource, Game>>> steps() {
        return null;
    }

}
