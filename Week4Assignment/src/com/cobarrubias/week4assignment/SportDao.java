package com.cobarrubias.week4assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SportDao {

	private Connection connection;
	private final String GET_SPORTS_QUERY = "SELECT * FROM sport";
	private final String ADD_SPORT_QUERY = "INSERT INTO sport(name) VALUES(?)";
	private final String DELETE_SPORT_QUERY = "DELETE FROM sport WHERE id = ?";
	private final String UPDATE_SPORT_QUERY = "UPDATE sport SET name = ? WHERE id = ?";
	
	public SportDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Sport> getSports() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_SPORTS_QUERY).executeQuery();
		List<Sport> sports = new ArrayList<Sport>();
		
		while (rs.next()) {
			sports.add(addSportToList(rs.getInt(1), rs.getString(2)));
		}
		return sports;
	}
	
	public void createNewSport(String sportName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ADD_SPORT_QUERY);
		ps.setString(1, sportName);
		ps.executeUpdate();
	}
	
	public void deleteSportById(int sportId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_SPORT_QUERY);
		ps.setInt(1, sportId);
		ps.executeUpdate();
	}
	
	public void updateSport(int sportId, String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_SPORT_QUERY);
		ps.setInt(2, sportId);
		ps.setString(1, name);
		ps.executeUpdate();
	}
	
	private Sport addSportToList(int id, String name) {
		return new Sport(id, name);
	}
}
