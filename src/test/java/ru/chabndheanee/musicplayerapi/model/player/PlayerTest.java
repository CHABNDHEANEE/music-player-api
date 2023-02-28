package ru.chabndheanee.musicplayerapi.model.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.chabndheanee.musicplayerapi.model.exception.PlayerException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {

    Player player;

    @BeforeEach
    void beforeEach() {
        player = new Player();
    }


    @Test
    void playSong_WithEmptyPlaylist_AndExpectError() {
        RuntimeException exception = assertThrows(PlayerException.class, () -> player.play());
        assertThat(exception.getMessage(), is("Playlist is empty"));
    }

    @Test
    void pauseNotPlayingSong_AndExpectError() {
        RuntimeException exception = assertThrows(PlayerException.class, () -> player.pause());
        assertThat(exception.getMessage(), is("Track is not playing!"));
    }

    @Test
    void addSong_AndExpectPlaylistWithOneSong() {
        addSong1();
        assertThat(player.getPlaylist().size(), is(1));
    }

    @Test
    void addSong_WithWrongPath_AndExpectError() {
        RuntimeException exception = assertThrows(PlayerException.class, () -> player.addSong("path"));
        assertThat(exception.getMessage(), is("File with track doesn't exist"));
    }

    @Test
    void addSong_WithDuplicate_AndExpectError() {
        RuntimeException exception = assertThrows(PlayerException.class, () -> {
            addSong1();
            addSong1();
        });
        assertThat(exception.getMessage(), is("The playlist already contains the track"));
    }

    @Test
    void playSong_WithSOngInPlaylist_AndExpectItPlaying() {
        addSong1();
        player.play();
        assertThat(player.isPlaying(), is(true));
    }

    @Test
    void deleteSong_WithOneSongInPlaylist_AndExpectPlaylistIsEmpty() {
        addSong1();
        assertThat(player.getPlaylist().size(), is(1));
        deleteSong1();
        assertThat(player.getPlaylist().size(), is(0));
    }

    @Test
    void testNext_AndTestPrev_WithTwoSongsInPlaylist_AndExpectSecondToBeCurrentTrack() {
        addSong1();
        addSong2();
        assertThat(player.getPlaylist().size(), is(2));
        player.play();
        player.next();
        assertThat(player.getCurrentTrack(), is(1));
        player.prev();
        assertThat(player.getCurrentTrack(), is(0));
    }

    @Test
    void testNext_WithLastTrackInPlaylist_AndExpectError() {
        addSong1();
        RuntimeException exception = assertThrows(PlayerException.class, () -> player.next());
        assertThat(exception.getMessage(), is("Playlist ended"));
    }

    @Test
    void testPrev_WithFirstTrack_AndExpectError() {
        addSong1();
        RuntimeException exception = assertThrows(PlayerException.class, () -> player.prev());
        assertThat(exception.getMessage(), is("It's already start of the playlist"));
    }

    @Test
    void testSavingAndLoading() {
        addSong1();
        assertThat(player.getPlaylist().size(), is(1));
        player.save();
        Player newPlayer = new Player();
        newPlayer.load();
        assertThat(newPlayer.getPlaylist().size(), is(player.getPlaylist().size()));
    }

    void addSong1() {
        player.addSong("532_full_dynamic-upbeat-logo_0012_preview");
    }

    void addSong2() {
        player.addSong("Metallica_-_Nothing_Else_Matters_(ColdMP3.com)");
    }

    void deleteSong1() {
        player.deleteSong("532_full_dynamic-upbeat-logo_0012_preview");
    }
}
