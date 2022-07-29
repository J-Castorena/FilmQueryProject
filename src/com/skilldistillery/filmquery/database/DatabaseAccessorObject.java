package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				film.setLastName(filmResult.getString("last_name"));
				film.setFirstName(filmResult.getString("first_name"));

				List<Actor> actors = findActorsByFilmId(filmId);
				film.setActors(actors);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Actor findActorById(int actorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

}
