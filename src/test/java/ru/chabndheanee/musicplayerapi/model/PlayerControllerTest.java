package ru.chabndheanee.musicplayerapi.model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class PlayerControllerTest {
    @Test
    void testPlaying_AndPausing_AndExpectNoError() {
        String input = "C:\\Users\\rusbe\\dev\\music-player-api\\src\\main\\java\\ru\\chabndheanee\\musicplayerapi\\src\\532_full_dynamic-upbeat-logo_0012_preview.wav";
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);
        PlayerController.addSong();
        PlayerController.play();
        PlayerController.pause();
    }
}
