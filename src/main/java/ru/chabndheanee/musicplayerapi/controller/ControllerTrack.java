package ru.chabndheanee.musicplayerapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.chabndheanee.musicplayerapi.model.PlayerController;
import ru.chabndheanee.musicplayerapi.model.player.Track;

@Slf4j
@RestController
public class ControllerTrack {

    @PutMapping("/track")
    private @ResponseBody Track putTrack(@RequestParam String name) {
        log.info("put track");

        return PlayerController.addSong(name);
    }

    @DeleteMapping("/track")
    private @ResponseBody Track deleteTrack(@RequestParam String name) {
        log.info("Song delete");

        return PlayerController.deleteSong(name);
    }

    @GetMapping("/track")
    private @ResponseBody String getTrack() {
        log.info("Get track");

        return PlayerController.getCurrentTrackName();
    }
}
