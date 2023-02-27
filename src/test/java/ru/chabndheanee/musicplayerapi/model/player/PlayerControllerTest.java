package ru.chabndheanee.musicplayerapi.model.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.chabndheanee.musicplayerapi.model.PlayerController;

import java.io.ByteArrayInputStream;

public class PlayerControllerTest {
    Player player;

    @BeforeEach
    void beforeEach() {
        player = new Player();
    }

    @Test
    void testPlaying_AndExpectNoError() {
        String input = "C:\\Users\\rusbe\\dev\\music-player-api\\src\\main\\java\\ru\\chabndheanee\\musicplayerapi\\src\\532_full_dynamic-upbeat-logo_0012_preview.wav";
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);
        PlayerController.addSong();
        PlayerController.play();
    }

    void addSong1() {
        player.addSong("C:\\Users\\rusbe\\dev\\music-player-api\\src\\main\\java\\ru\\chabndheanee\\musicplayerapi\\src\\532_full_dynamic-upbeat-logo_0012_preview.wav");
    }

    void addSong2() {
        player.addSong("C:\\Users\\rusbe\\dev\\music-player-api\\src\\main\\java\\ru\\chabndheanee\\musicplayerapi\\src\\Metallica_-_Nothing_Else_Matters_(ColdMP3.com).wav");
    }

    void deleteSong1() {
        player.deleteSong("C:\\Users\\rusbe\\dev\\music-player-api\\src\\main\\java\\ru\\chabndheanee\\musicplayerapi\\src\\532_full_dynamic-upbeat-logo_0012_preview.wav");
    }
}
