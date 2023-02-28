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

        save();

        thread = new SongThread();
        thread.setDuration(track.getDuration());
        thread.start();
    }

    public void pause() {
        log.info("Player pause");

        checkTrackPlaying();

        track.pause();
        playing = false;

        save();

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

        save();

        stop();
        play();
    }

    public Track addSong(String path) {
        log.info("Player addSong");

        path = "src/main/java/ru/chabndheanee/musicplayerapi/src/" + path + ".wav";
        try {
            Track addedTrack = new Track(new File(path));

            if (playlist.contains(addedTrack)) {
                throwException(new PlayerException("The playlist already contains the track"));
            }
            playlist.add(addedTrack);
            return addedTrack;
        } catch (IOException e) {
            throwException(new PlayerException("File with track doesn't exist"));
        }

        save();
        return null;
    }

    public void deleteSong(String path) {
        log.info("Player deleteSong");

        path = "src/main/java/ru/chabndheanee/musicplayerapi/src/" + path + ".wav";

        try {
            Track deletingTrack = new Track(new File(path));

            if (playlist.contains(deletingTrack)) {
                int indexOfTrack = playlist.indexOf(deletingTrack);
                if (indexOfTrack != currentTrack || !playing) {
                    if (indexOfTrack < currentTrack) {
                        currentTrack--;
                    } else if (indexOfTrack == currentTrack) {
                        if (currentTrack != 0) {
                            if (playlist.size() - 1 != currentTrack)  {
                                currentTrack++;
                            } else {
                                currentTrack--;
                            }
                        }
                    }
                    playlist.remove(deletingTrack);
                } else {
                    throwException(new PlayerException("You can't delete track that is playing now"));
                }
            } else {
                throwException(new PlayerException("The playlist doesn't contains the track"));
            }
        } catch (IOException e) {
            throwException(new PlayerException("File with track doesn't exist"));
        }

        save();
    }

    public void save() {
        log.info("Player save");

        if (playlist.isEmpty()) {
            return;
        }

        try (Writer writer = new FileWriter("Playlist.csv")) {
            for (Track track :
                    playlist) {
                writer.write(track.getTrackFile().getAbsolutePath());
                writer.write("\n");
            }
            writer.write(String.valueOf(currentTrack));
            writer.write("\n");
            writer.write(String.valueOf(playlist.get(currentTrack).getClipPos()));
        } catch (IOException e) {
            throwException(new PlayerException("Error during saving in file. " + e.getMessage()));
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

        for (int i = 0; i < strings.length; i++) {
            if (i == strings.length - 2) {
                currentTrack = Integer.parseInt(strings[i]);
            } else if (i == strings.length - 1) {
                playlist.get(currentTrack).setClipPos(Long.parseLong(strings[i]));
            } else {
                File file = new File(strings[i]);
                try {
                    playlist.add(new Track(file));
                } catch (IOException ignored) {}
            }
        }

        track = playlist.get(currentTrack);
    }

    private void checkTrackPlaying() {
        if (track == null || !playing) {
            throwException(new PlayerException("Track is not playing!"));
        }
    }

    private void checkPlaylistIsEmpty() {
        if (playlist.isEmpty()) {
            throwException(new PlayerException("Playlist is empty"));
        }
    }

    private void checkPlaylistEnded() {
        if (currentTrack + 1 == playlist.size()) {
            throwException(new PlayerException("Playlist ended"));
        }
    }

    private void checkStartOfPlaylist() {
        if (currentTrack == 0) {
            throwException(new PlayerException("It's already start of the playlist"));
        }
    }

    private void throwException(RuntimeException exc) {
        thread.interrupt();
        throw exc;
    }
}

