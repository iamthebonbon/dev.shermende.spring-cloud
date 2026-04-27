package dev.shermende.game.processor.gamecreateprocessor;

import dev.shermende.game.processor.gamecreateprocessor.step.GameCreateProcessorGameStep;
import dev.shermende.game.processor.gamecreateprocessor.step.GameCreateProcessorPrepareStep;
import dev.shermende.game.processor.gamecreateprocessor.step.GameCreateProcessorRoutesStep;
import dev.shermende.support.spring.processor.AbstractStepProcessor;
import dev.shermende.support.spring.processor.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
@Component
public class GameCreateProcessor extends AbstractStepProcessor<GameCreateProcessorCtx, GameCreateProcessorCtx> {

    protected GameCreateProcessor(
            BeanFactory factory
    ) {
        super(factory);
    }

    @Override
    protected Collection<Class<? extends Step<GameCreateProcessorCtx, GameCreateProcessorCtx>>> steps() {
        return Arrays.asList(
                GameCreateProcessorPrepareStep.class,
                GameCreateProcessorGameStep.class,
                GameCreateProcessorRoutesStep.class
        );
    }

    @Override
    @Transactional
    public GameCreateProcessorCtx execute(
            GameCreateProcessorCtx input
    ) {
        return super.execute(input);
    }
}
