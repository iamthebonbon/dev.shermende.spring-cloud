package dev.shermende.game.service;

import dev.shermende.game.db.entity.Route;
import dev.shermende.lib.dal.service.CrudService;

import java.util.List;

public interface GameRouteService extends CrudService<Route, Long> {

    List<Route> findAllByPoint(
            Long gameId,
            Long pointId
    );


}
