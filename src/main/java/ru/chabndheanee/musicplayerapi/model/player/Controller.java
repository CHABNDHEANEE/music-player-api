package ru.chabndheanee.musicplayerapi.model.player;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public abstract class Controller {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Player player = new Player();


    @SuppressWarnings("InfiniteLoopStatement")
    public static void launch() {
        while (true) {
            printMenu();
            loop:
            while (true) {
                switch (scanner.nextInt()) {
                    case 1:
                        play();
                        break loop;
                    case 2:
                        next();
                        break loop;
                    case 3:
                        prev();
                        break loop;
                    case 4:
                        pause();
                        break loop;
                    case 5:
                        addSong();
                        break loop;
                    default:
                        System.out.println("Wrong command, please try another");
                }
            }
        }
    }

    private static void printMenu() {
        log.info("Print menu");
        System.out.println("1. Play");
        System.out.println("2. Next");
        System.out.println("3. Prev");
        System.out.println("4. Stop");
        System.out.println("5. Add Song");
        System.out.println("-------------");
    }
    public static void play() {
        log.info("Controller play");
        player.play();
    }

    public static void pause() {
        log.info("Controller pause");
        player.pause();
    }

    public static void addSong() {
        log.info("Controller addSong");
        System.out.println("Enter song path");
        player.addSong(scanner.next());
    }

    public static void next() {
        log.info("Controller next");
        player.next();
    }

    public static void prev() {
        log.info("Controller prev");
        player.prev();
    }
}