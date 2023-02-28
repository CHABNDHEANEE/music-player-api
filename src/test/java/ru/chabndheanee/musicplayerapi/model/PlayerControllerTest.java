package ru.chabndheanee.musicplayerapi.model;

import org.junit.jupiter.api.Test;

public class PlayerControllerTest {
    @Test
    void testPlaying_AndPausing_AndExpectNoError() {
        PlayerController.addSong("532_full_dynamic-upbeat-logo_0012_preview");
        PlayerController.addSong("Metallica_-_Nothing_Else_Matters_(ColdMP3.com)");
        PlayerController.play();
        PlayerController.pause();
        PlayerController.play();
        PlayerController.next();
        PlayerController.prev();
        PlayerController.deleteSong("Metallica_-_Nothing_Else_Matters_(ColdMP3.com)");
    }
}
