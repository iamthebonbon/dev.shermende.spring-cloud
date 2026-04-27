package dev.shermende.reference.controller;

import dev.shermende.reference.assembler.MovementReasonModelAssembler;
import dev.shermende.reference.exception.NotFoundException;
import dev.shermende.reference.lib.api.MovementReasonApiService;
import dev.shermende.reference.lib.model.MovementReasonModel;
import dev.shermende.reference.service.MovementReasonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class MovementReasonController implements MovementReasonApiService {

    private final MovementReasonService service;
    private final MovementReasonModelAssembler assembler;

    @Override
    public MovementReasonModel findById(
            @NotNull @PathVariable Long id
    ) {
        return assembler.toModel(service.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public List<Long> findIdsByScenarioId(
            @NotNull @RequestParam("scenario-id") Long scenarioId
    ) {
        return service.findIdsByScenarioId(scenarioId);
    }

}
