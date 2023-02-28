package ru.chabndheanee.musicplayerapi.model.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Data
public class Track {
    private String name;
    private Long duration;
    @JsonIgnore
    private File trackFile;
    @JsonIgnore
    private Clip clip;
    @JsonIgnore
    private AudioInputStream ais;
    @JsonIgnore
    private FileInputStream fileInputStream;
    @JsonIgnore
    private long clipPos = 0;
    @JsonIgnore
    private boolean playing = false;

    public Track(File trackFile) throws IOException {
        this.trackFile = trackFile;
        name = trackFile.getName();
        fileInputStream = new FileInputStream(trackFile);
        duration = Objects.requireNonNull(fileInputStream).getChannel().size() / 128;
        try {
            clip = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(trackFile);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void play() {
        log.info("Track play");
        try {
            clip = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(trackFile);
            clip.open(ais);
            if (clipPos != 0) {
                clip.setMicrosecondPosition(clipPos);
            }
            clip.start();
            playing = true;
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void pause() {
        log.info("Track pause");
        clipPos = clip.getMicrosecondPosition();
        clip.stop();
        clip.close();
    }

    public void stop() {
        log.info("Track stop");

        clip.stop();
        clip.close();
    }

    public long getPosition() {
        log.info("Track getPosition");
        return clip.getMicrosecondPosition();
    }

    public void setPosition(long position) {
        log.info("Track setPosition");
        clipPos = position;
    }

    public boolean isPlaying() {
        return playing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return name.equals(track.name) && duration.equals(track.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration);
    }
}
