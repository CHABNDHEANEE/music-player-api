package ru.chabndheanee.musicplayerapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chabndheanee.musicplayerapi.model.PlayerController;

@Slf4j
@RestController
public class ControllerPlayer {
    @GetMapping("/player/play")
    public void play() {
        log.info("Play");


        PlayerController.play();

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
}

