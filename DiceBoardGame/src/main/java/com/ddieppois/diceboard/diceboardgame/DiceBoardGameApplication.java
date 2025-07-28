package com.ddieppois.diceboard.diceboardgame;

import com.ddieppois.diceboard.diceboardgame.services.DiceBoardGameService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DiceBoardGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiceBoardGameApplication.class, args);
	}


	@Bean
	public CommandLineRunner run(DiceBoardGameService gameService) {
		return args -> gameService.startGame();
	}

}
