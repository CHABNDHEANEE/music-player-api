package ru.chabndheanee.musicplayerapi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.chabndheanee.musicplayerapi.model.PlayerController;

@SpringBootApplication
public class MusicPlayerApiApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MusicPlayerApiApplication.class, args);
		PlayerController.launch();
	}

}
