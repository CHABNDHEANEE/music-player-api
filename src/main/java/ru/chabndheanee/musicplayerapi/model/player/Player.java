package ru.chabndheanee.musicplayerapi.model.player;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.chabndheanee.musicplayerapi.model.exception.PlayerException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

@SuppressWarnings("deprecation")
@Slf4j
@Data
public class Player {
    private final LinkedList<Track> playlist = new LinkedList<>();
    private Track track;
    private int currentTrack = 0;
    private SongThread thread = new SongThread();
    private boolean playing = false;


    public void play() {
        log.info("Player play");

        thread.interrupt();

        checkPlaylistIsEmpty();

        playing = true;

        track = playlist.get(currentTrack);

        track.play();

        thread = new SongThread();
        thread.setDuration(track.getDuration());
        thread.start();
    }

    public void pause() {
        log.info("Player pause");

        checkTrackPlaying();

        track.pause();
        playing = false;
        thread.stop();
    }

    private void stop() {
        log.info("Player stop");

        checkTrackPlaying();

        track.stop();
        playing = false;
    }

    public void next() {
        log.info("Player next");

        checkPlaylistEnded();

        currentTrack++;

        if (playing) {
            stop();
        }

        play();
    }

    public void prev() {
        log.info("Player prev");

        checkStartOfPlaylist();

        if (track.getPosition() > 10000000) {
            pause();
            track.setPosition(0);
            play();
            return;
        }

        currentTrack--;
        stop();
        play();
    }

    public void addSong(String path) {
        log.info("Player addSong");
        try {
            Track addedTrack = new Track(new File(path));
            playlist.add(addedTrack);
        } catch (IOException e) {
            throw new PlayerException("File with track doesn't exist");
        }

        save();
    }

    public void save() {
        log.info("Player save");

        try (Writer writer = new FileWriter("Playlist.csv")) {
            for (Track track :
                    playlist) {
                writer.write(track.getTrackFile().getAbsolutePath());
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new PlayerException("Error during saving in file. " + e.getMessage());
        }

        log.info("Player saved");
    }

    public void load() {
        log.info("Player load");

        if (!new File("C:\\Users\\rusbe\\dev\\music-player-api\\Playlist.csv").exists()) {
            return;
        }

        String strFile = null;

        try {
            strFile = Files.readString(Path.of("C:\\Users\\rusbe\\dev\\music-player-api\\Playlist.csv"));
        } catch (IOException ignored) {}

        if (strFile == null || strFile.isBlank()) {
            return;
        }

        String[] strings = strFile.split("\n");

        for (String str :
                strings) {
            addSong(str);
        }

        track = playlist.get(0);
    }

    private void checkTrackPlaying() {
        if (track == null || !playing) {
            thread.interrupt();
            throw new PlayerException("Track is not playing!");
        }
    }

    private void checkPlaylistIsEmpty() {
        if (playlist.isEmpty()) {
            thread.interrupt();
            throw new PlayerException("Playlist is empty");
        }
    }

    private void checkPlaylistEnded() {
        if (currentTrack + 1 == playlist.size()) {
            thread.interrupt();
            throw new PlayerException("Playlist ended");
        }
    }

    private void checkStartOfPlaylist() {
        if (currentTrack == 0) {
            thread.interrupt();
            throw new PlayerException("It's already start of the playlist");
        }
    }
}

