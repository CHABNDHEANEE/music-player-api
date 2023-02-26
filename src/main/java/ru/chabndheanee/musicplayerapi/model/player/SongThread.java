package ru.chabndheanee.musicplayerapi.model.player;

class SongThread extends Thread {
    long duration = 0;

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            Controller.next();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
