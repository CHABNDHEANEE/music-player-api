package ru.chabndheanee.musicplayerapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.chabndheanee.musicplayerapi.model.PlayerController;

@Slf4j
@RestController
public class ControllerPlayer {
    @GetMapping("/player/play")
    public void play() {
        log.info("Play");

        PlayerController.play();
        ok();
    }

    @GetMapping("/player/pause")
    private void pause() {
        log.info("Pause");

        PlayerController.pause();
    }

    @GetMapping("/player/next")
    private void next() {
        log.info("Next");

        PlayerController.next();
    }

    @GetMapping("/player/prev")
    private void prev() {
        log.info("Prev");

        PlayerController.prev();
    }

    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    private void ok() {}
}

