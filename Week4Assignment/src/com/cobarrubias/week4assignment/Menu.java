package com.cobarrubias.week4assignment;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

	private SportDao sportDao = new SportDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display sports", 
			"Add a sport", 
			"Update a sport", 
			"Delete a sport"
			);
	
	public void start() {
		String selection = "";
		
		do {
			
			printMenu();
			selection = scanner.nextLine();
			try {
				if (selection.equals("1")) {
					readSport();
				} else if (selection.equals("2")) {
					createSport();
				} else if (selection.equals("3")) {
					updateSport();
				} else if (selection.equals("4")) {
					deleteSport();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue...");
			scanner.nextLine();
		} while (!selection.equals("-1"));
	}
	
	private void printMenu() {
		System.out.println("Select an option:\n---------------------------------");
		
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void readSport() throws SQLException {
		List<Sport> sports = sportDao.getSports();
		for (Sport sport : sports) {
			System.out.println(sport.getSportId() + ": " + sport.getSportName());
		}
	}
	
	private void createSport() throws SQLException {
		System.out.print("Enter new sport name: ");
		String sportName = scanner.nextLine();
		sportDao.createNewSport(sportName);
	}
	
	private void updateSport() throws SQLException {
		System.out.print("Enter the id of the sport you wish to update: ");
		int sportId = Integer.parseInt(scanner.nextLine());
		System.out.print("Update sport: ");
		String sportName = scanner.nextLine();
		sportDao.updateSport(sportId, sportName);
		System.out.println("Sport has been updated!");
	}
	
	private void deleteSport() throws SQLException {
		System.out.print("Enter id of sport to delete: ");
		int sportId = Integer.parseInt(scanner.nextLine());
		sportDao.deleteSportById(sportId);
	}
}
