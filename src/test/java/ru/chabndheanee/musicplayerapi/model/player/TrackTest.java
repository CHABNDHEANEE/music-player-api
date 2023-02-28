package ru.chabndheanee.musicplayerapi.model.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TrackTest {
    Track track;

    @BeforeEach
    void beforeEach() throws IOException {
        track = new Track(new File("src/main/resources/Tracks/532_full_dynamic-upbeat-logo_0012_preview.wav"));

    }

    @Test
    void playTest_AndExpectPlaying() {
        track.play();
        assertThat(track.isPlaying(), is(true));
    }
}
