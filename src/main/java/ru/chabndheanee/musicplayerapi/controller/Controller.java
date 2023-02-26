package ru.chabndheanee.musicplayerapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.chabndheanee.musicplayerapi.model.player.Track;

@Slf4j
@RestController
public class Controller {

    @PutMapping
    private void putTrack(@RequestBody Track track) {

    }
}
