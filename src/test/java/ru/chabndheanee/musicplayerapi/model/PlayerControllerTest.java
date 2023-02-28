package ru.chabndheanee.musicplayerapi.model;

import org.junit.jupiter.api.Test;

public class PlayerControllerTest {
    @Test
    void testPlaying_AndPausing_AndExpectNoError() {
        PlayerController.addSong("C:\\Users\\rusbe\\dev\\music-player-api\\src\\main\\java\\ru\\chabndheanee\\musicplayerapi\\src\\532_full_dynamic-upbeat-logo_0012_preview.wav");
        PlayerController.addSong("C:\\Users\\rusbe\\dev\\music-player-api\\src\\main\\java\\ru\\chabndheanee\\musicplayerapi\\src\\Metallica_-_Nothing_Else_Matters_(ColdMP3.com).wav");
        PlayerController.play();
        PlayerController.pause();
        PlayerController.play();
        PlayerController.next();
        PlayerController.prev();
        PlayerController.deleteSong("C:\\Users\\rusbe\\dev\\music-player-api\\src\\main\\java\\ru\\chabndheanee\\musicplayerapi\\src\\Metallica_-_Nothing_Else_Matters_(ColdMP3.com).wav");
    }
}
