package ru.chabndheanee.musicplayerapi.model.player;

import lombok.extern.slf4j.Slf4j;
import ru.chabndheanee.musicplayerapi.model.exception.PlayerException;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

@SuppressWarnings("deprecation")
@Slf4j
//@Data
public class Player {
    final LinkedList<Track> playlist = new LinkedList<>();
    Track track;
    int currentTrack = 0;
    SongThread thread = new SongThread();


    public void play() {
        log.info("Player play");

        if (playlist.isEmpty()) {
            throw new PlayerException("Playlist is empty");
        } else if (playlist.size() < currentTrack) {
            throw new PlayerException("Playlist ended");
        }

        track = playlist.get(currentTrack);

        track.play();
        track.setDuration();

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
        thread.stop();
    }

    public void next() {
        log.info("Player next");
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
            e.getStackTrace();
        }
    }
}

