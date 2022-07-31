package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

//	private void test() {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//		Actor actor = db.findActorById(2000);
//		System.out.println(actor);
//		List<Actor> actors = db.findActorsByFilmId(2);
//		for (Actor actor2 : actors) {
//			System.out.println(actor2);
//		}
//		
//		System.out.println(actors);
//	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		// TODO Display menu
		// film not found if null
		boolean run = true;
		while (run) {
			try {
				displayMenu();
				int userInput = input.nextInt();
				input.nextLine();
				switch (userInput) {
				case 1:
					findFilmById(input);
					break;
				case 2:
					lookUpFilmByKeyword(input);
					break;

				}
			} catch (InputMismatchException e) {
				System.out.println("Please choose a valid option.");
				input.nextLine();
			}
		}
	}

	private void displayMenu() {
		System.out.println("+-------------------------------------+");
		System.out.println("|              Film Query             |");
		System.out.println("|                                     |");
		System.out.println("| 1. Look up film by id               |");
		System.out.println("| 2. Look up film by keyword          |");
		System.out.println("| 3. Exit                             |");
		System.out.println("|                                     |");
		System.out.println("+-------------------------------------+");
	}

	private void findFilmById(Scanner input) {
		System.out.println("Please enter the ID of the film you are looking for: ");
		int userInput = input.nextInt();
		Film film = db.findFilmById(userInput);
		if (film != null) {
			showFilmInfo(film);
		}
	}

	private void showFilmInfo(Film film) {
		System.out.println("The following film was found: ");
		System.out.println("Title: " + film.getTitle() + ", Release Year: " + film.getReleaseYear() + ", Language: " + film.getFilmLanguage() + ", Rating: "
				+ film.getRating() + ", Description: " + film.getDescription() 
				+ ".\nCast: " + film.getActors());
	}

	private void lookUpFilmByKeyword(Scanner input) {
		System.out.println("Please enter a keyword for the film you are looking for: ");
		String userInput = input.nextLine();
		List<Film> films = db.findFilmByKeyword(userInput);
		if (films.size() == 0) {
			System.out.println("No films were found. ");
		}
		if (films != null) {
			for (Film film : films) {
				showFilmInfo(film);
			}
		}
	}
}
