package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private final String user = "student";
	private final String pass = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT * FROM film WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getString("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				film.setFilmLanguage(getLanguageForFilm(filmId));
				film.setActors(findActorsByFilmId(filmId));

			} else {
				System.out.println("Film ID is invalid, please select between 1 - 1000.");
				return film = null;
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));

			} else {
				System.out.println("Actor ID is invalid, please select between 1 - 200.");
				return actor = null;
			}
			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT DISTINCT actor.id, actor.first_name, actor.last_name"
				+ " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id"
				+ " JOIN film ON film_actor.film_id = film_id" + " WHERE film_id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet result = stmt.executeQuery();
			while (result.next()) {

				int actorId = result.getInt("id");
				String actorFirstName = result.getString("first_name");
				String actorLastName = result.getString("last_name");
				Actor actor = new Actor(actorId, actorFirstName, actorLastName);
				actors.add(actor);

			}
			result.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				int id = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				String releaseYear = results.getString("release_year");
				int languageId = results.getInt("language_id");
				int rentalDuration = results.getInt("rental_duration");
				double rentalRate = results.getDouble("rental_rate");
				int length = results.getInt("length");
				double replacementCost = results.getDouble("replacement_cost");
				String rating = results.getString("rating");
				String specialFeatures = results.getString("special_features");
				String language = getLanguageForFilm(id);
				List<Actor> actors = findActorsByFilmId(id);
				Film film = new Film(id, title, description, releaseYear, languageId, language, rentalDuration, rentalRate,
						length, replacementCost, rating, specialFeatures, actors);
				films.add(film);
			}

			results.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	public String getLanguageForFilm(int filmId) {
		String filmLanguage = "";
		String sql = "SELECT language.name FROM language JOIN film ON film.language_id = language.id WHERE film.id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				filmLanguage = result.getString(1);
			}
			result.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmLanguage;

	}

}
