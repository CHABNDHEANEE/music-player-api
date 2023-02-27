package ru.chabndheanee.musicplayerapi.model.player;

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
//@Data
public class Player {
    private final LinkedList<Track> playlist = new LinkedList<>();
    Track track;
    int currentTrack = 0;
    SongThread thread = new SongThread();


    public void play() {
        log.info("Player play");

        thread.interrupt();

        if (playlist.isEmpty()) {
            throw new PlayerException("Playlist is empty");
        } else if (playlist.size() < currentTrack) {
            throw new PlayerException("Playlist ended");
        }

        track = playlist.get(currentTrack);

        track.play();

        thread = new SongThread();
        thread.setDuration(track.getDuration());
        thread.start();
    }

    public void pause() {
        log.info("Player pause");
        track.pause();
        thread.stop();
    }

    private void stop() {
        log.info("Player stop");
        track.stop();
//        thread.interrupt();
    }

    public void next() {
        log.info("Player next");
//        thread.stop();
        currentTrack++;
        stop();
        play();
    }

    public void prev() {
        log.info("Player prev");

        if (track.getPosition() > 10000000) {
            pause();
            track.setPosition(0);
            play();
            return;
        }

        if (currentTrack == 0) {
            throw new PlayerException("It's already start of the playlist");
        }

//        thread.stop();

        currentTrack--;
        stop();
        play();
    }

    public void addSong(String path) {
        log.info("Player addSong");
        try {
            Track track = new Track(new File(path));
            playlist.add(track);
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
    }
}

