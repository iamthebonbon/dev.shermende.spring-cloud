package dev.shermende.game.service.impl;

import dev.shermende.game.db.entity.QRoute;
import dev.shermende.game.db.entity.Route;
import dev.shermende.game.db.repository.RouteRepository;
import dev.shermende.game.service.GameRouteService;
import dev.shermende.lib.dal.service.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RouteServiceImpl extends AbstractCrudService<Route, Long, QRoute>
        implements GameRouteService {

    private final RouteRepository repository;

    public RouteServiceImpl(
            RouteRepository repository
    ) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Route> findAllByPoint(
            Long gameId,
            Long pointId
    ) {
        return findAll(
                QRoute.route.gameId.eq(gameId)
                        .and(
                                QRoute.route.sourcePointId.eq(pointId)
                                        .or(QRoute.route.targetPointId.eq(pointId))
                        )
        );
    }
}
