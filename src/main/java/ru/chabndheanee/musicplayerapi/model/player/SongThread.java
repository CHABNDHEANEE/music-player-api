package ru.chabndheanee.musicplayerapi.model.player;

import ru.chabndheanee.musicplayerapi.model.PlayerController;

class SongThread extends Thread {
    private long duration = 0;

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            PlayerController.next();
        } catch (InterruptedException ignored) {}
    }
}
