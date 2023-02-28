package ru.chabndheanee.musicplayerapi.model;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.Writer;

public class PlayerControllerTest {
    @Test
    void testPlaying_AndPausing_AndExpectNoError() {
        try (Writer writer = new FileWriter("src/main/resources/Playlist.csv")) {
            writer.write("");
        } catch (Exception ignored) {}

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
