package ru.chabndheanee.musicplayerapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.chabndheanee.musicplayerapi.model.PlayerController;
import ru.chabndheanee.musicplayerapi.model.player.Track;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ControllerPlaylist {
    @GetMapping("/playlist")
    private @ResponseBody List<Track> getPlaylist() {
        log.info("Get playlist");

        return new ArrayList<>(PlayerController.getPlaylist());
    }
}
