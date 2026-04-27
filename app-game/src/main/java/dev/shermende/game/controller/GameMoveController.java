package dev.shermende.game.controller;

import dev.shermende.game.assembler.GameModelAssembler;
import dev.shermende.game.service.GameService;
import dev.shermende.game.validator.GameMoveResourceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameMoveController {

    private final GameService service;
    private final GameModelAssembler assembler;
    private final GameMoveResourceValidator validator;

    @InitBinder("gameMoveResource")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

//    @PutMapping("/{id}/move")
//    public GameModel move(
//            @AuthenticationPrincipal Authentication authentication,
//            @PathVariable Long id,
//            @Validated @RequestBody GameMoveResource resource
//    ) {
//        return assembler.toModel(service.move(authentication, id, resource));
//    }

}
