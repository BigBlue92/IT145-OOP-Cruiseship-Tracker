/*IT145 T5565
 *Ryan Mackenzie
 *Project Three: Cruise ship program
 *Version 1.0 
 */


package p3;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import java.util.InputMismatchException;

public class Driver {

	// instance variables (add more as needed)
	private static ArrayList<Ship> shipList = new ArrayList();
	private static ArrayList<Cruise> cruiseList = new ArrayList();
	private static ArrayList<Passenger> passengerList = new ArrayList();

	public static void main(String[] args) {

		initializeShipList(); // initial ships
		initializeCruiseList(); // initial cruises
		initializePassengerList(); // initial passengers
		String userInput = "";

		while (!userInput.equalsIgnoreCase("x")) {
			displayMenu();
			Scanner scnr = new Scanner(System.in);
			int userInt = -1;
			// add loop and code here that accepts and validates user input
			// and takes the appropriate action. include appropriate
			// user feedback and redisplay the menu as needed
			if (scnr.hasNextInt()) {
				userInt = scnr.nextInt();
				if (userInt == 1) {
					addShip();
				} else if (userInt == 2) {
					editShip();
				} else if (userInt == 3) {
					addCruise();
				} else if (userInt == 4) {
					editCruise();
				} else if (userInt == 5) {
					addPassenger();
				} else if (userInt == 6) {
					editPassenger();
				}
			} else {
				userInput = scnr.nextLine();
				if (userInput.equalsIgnoreCase("a")) {
					printShipList("name");
				} else if (userInput.equalsIgnoreCase("b")) {
					printShipList("active");
				} else if (userInput.equalsIgnoreCase("c")) {
					printShipList("full");
				} else if (userInput.equalsIgnoreCase("d")) {
					printCruiseList("list");
				} else if (userInput.equalsIgnoreCase("e")) {
					printCruiseList("details");
				} else if (userInput.equalsIgnoreCase("f")) {
					printPassengerList();
				}
			}
		}

	}

	// hardcoded ship data for testing
	// Initialize ship list
	public static void initializeShipList() {
		add("Candy Cane", 20, 40, 10, 60, true);
		add("Peppermint Stick", 10, 20, 5, 40, true);
		add("Bon Bon", 12, 18, 2, 24, false);
		add("Candy Corn", 12, 18, 2, 24, false);
	}

	// hardcoded cruise data for testing
	// Initialize cruise list
	public static void initializeCruiseList() {
		Cruise newCruise = new Cruise("Southern Swirl", "Candy Cane", "Miami", "Cuba", "Miami");
		cruiseList.add(newCruise);
	}

	// hardcoded cruise data for testing
	// Initialize passenger list
	public static void initializePassengerList() {
		Passenger newPassenger1 = new Passenger("Neo Anderson", "Southern Swirl", "STE");
		passengerList.add(newPassenger1);

		Passenger newPassenger2 = new Passenger("Trinity", "Southern Swirl", "STE");
		passengerList.add(newPassenger2);

		Passenger newPassenger3 = new Passenger("Morpheus", "Southern Swirl", "BAL");
		passengerList.add(newPassenger3);
	}

	// custom method to add ships to the shipList ArrayList
	public static void add(String tName, int tBalcony, int tOceanView, int tSuite, int tInterior, boolean tInService) {
		Ship newShip = new Ship(tName, tBalcony, tOceanView, tSuite, tInterior, tInService);
		shipList.add(newShip);
	}

	public static void printShipList(String listType) {
		// printShipList() method prints list of ships from the
		// shipList ArrayList. There are three different outputs
		// based on the listType String parameter:
		// name - prints a list of ship names only
		// active - prints a list of ship names that are "in service"
		// full - prints tabbed data on all ships

		if (shipList.size() < 1) {
			System.out.println("\nThere are no ships to print.");
			return;
		}
		if (listType == "name") {
			System.out.println("\n\nSHIP LIST - Name");
			for (int i = 0; i < shipList.size(); i++) {
				System.out.println(shipList.get(i));
			}
		} else if (listType == "active") {
			System.out.println("\n\nSHIP LIST - Active");

			// I added this chunk to print ship data only if the ship is in service.
			System.out.println("-----------------------------------------------");
			System.out.println("                    Number of Rooms     In");
			System.out.print("SHIP NAME           Bal OV  Ste Int     Service");
			System.out.println("\n-----------------------------------------------");
			boolean inService = false;
			for (Ship eachShip : shipList)
				if (eachShip.getInService()) {
					inService = true;
					eachShip.printShipData();
				}
			// Lets the user know there are no ships in service.
			if (!inService) {
				System.out.println("There are currently no ships in service.");
			}

		} else if (listType == "full") {
			System.out.println("\n\nSHIP LIST - Full");
			System.out.println("-----------------------------------------------");
			System.out.println("                    Number of Rooms     In");
			System.out.print("SHIP NAME           Bal OV  Ste Int     Service");
			System.out.println("\n-----------------------------------------------");
			for (Ship eachShip : shipList)
				eachShip.printShipData();

		} else
			System.out.println("\n\nError: List type not defined.");
	}

	public static void printCruiseList(String listType) {
		if (cruiseList.size() < 1) {
			System.out.println("\nThere are no cruises to print.");
			return;
		}
		if (listType == "list") {
			System.out.println("\n\nCRUISE LIST");
			for (int i = 0; i < cruiseList.size(); i++) {
				System.out.println(cruiseList.get(i));
			}
		} else if (listType == "details") {
			System.out.println("\n\nCRUISE LIST - Details");
			System.out.println(
					"------------------------------------------------------------------------------------------");
			System.out.println(
					"                                      |----------------------PORTS-----------------------|");
			System.out.print("CRUISE NAME         SHIP NAME           DEPARTURE           DESTINATION         RETURN");
			System.out.println(
					"\n------------------------------------------------------------------------------------------");
			for (Cruise eachCruise : cruiseList)
				eachCruise.printCruiseDetails();
		} else
			System.out.println("\n\nError: List type not defined.");
	}

	public static void printPassengerList() {
		if (passengerList.size() < 1) {
			System.out.println("\nThere are no passengers to print.");
			return;
		}
		System.out.println("\n\nPASSENGER LIST");
		System.out.println("-----------------------------------------------------");
		System.out.print("PASSENGER NAME      CRUISE              ROOM TYPE");
		System.out.println("\n-----------------------------------------------------");
		for (Passenger eachPassenger : passengerList)
			eachPassenger.printPassenger();
	}

	// display text-based menu
	public static void displayMenu() {

		System.out.println("\n\n");
		System.out.println("         Luxury Ocean Cruise Outings");
		System.out.println("               System Menu\n");
		System.out.println("[1] Add Ship            [A] Print Ship Names");
		System.out.println("[2] Edit Ship           [B] Print Ship In Service List");
		System.out.println("[3] Add Cruise          [C] Print Ship Full List");
		System.out.println("[4] Edit Cruise         [D] Print Cruise List");
		System.out.println("[5] Add Passenger       [E] Print Cruise Details");
		System.out.println("[6] Edit Passenger      [F] Print Passenger List");
		System.out.println("[x] Exit System");
		System.out.println("\nEnter a menu selection: ");
	}

	/*
	 * Add a New Ship. Loops and allows for the addition of multiple ships. Also
	 * validates each time input is collected. Forces user to input data before
	 * moving to the next item.
	 */
	public static void addShip() {
		String shipName = "";
		int roomBalcony = -1;
		int roomOceanView = -1;
		int roomSuite = -1;
		int roomInterior = -1;
		boolean inService = true;
		boolean newShip;
		String quit = "Y";

		// Do while loop allows user to add multiple ships until they're done.
		do {
			Scanner shipScnr = new Scanner(System.in);

			// Validates then adds a ship name. Loops until a name is entered.
			// Also validates ship is not already entered.
			System.out.println("Please enter the ship name: ");
			do {
				newShip = true;
				shipName = shipScnr.nextLine();
				while (shipName.isEmpty()) {
					System.out.println("No name was entered, please enter a name.");
					shipName = shipScnr.nextLine();
				}
				for (Ship eachShip : shipList) {
					if (shipName.equalsIgnoreCase(eachShip.getShipName())) {
						System.out.println("Ship already exists in the system. Please try again.");
						newShip = false;
					}
				}
			} while (!newShip);

			// Validates and adds room numbers. Loops until input is correct each.
			System.out.println("Please enter # of balcony rooms: ");
			if (!shipScnr.hasNextInt()) {
				while (!shipScnr.hasNextInt()) {
					shipScnr.nextLine();
					System.out.println("Please enter a valid number");
				}
				roomBalcony = shipScnr.nextInt();
			} else {
				roomBalcony = shipScnr.nextInt();
			}

			System.out.println("Please enter # of ocean view rooms: ");
			if (!shipScnr.hasNextInt()) {
				while (!shipScnr.hasNextInt()) {
					shipScnr.nextLine();
					System.out.println("Please enter a valid number");
				}
				roomOceanView = shipScnr.nextInt();
			} else {
				roomOceanView = shipScnr.nextInt();
			}

			System.out.println("Please enter # of suites: ");
			if (!shipScnr.hasNextInt()) {
				while (!shipScnr.hasNextInt()) {
					shipScnr.nextLine();
					System.out.println("Please enter a valid number");
				}
				roomSuite = shipScnr.nextInt();
			} else {
				roomSuite = shipScnr.nextInt();
			}

			System.out.println("Please enter # of interior rooms: ");
			if (!shipScnr.hasNextInt()) {
				while (!shipScnr.hasNextInt()) {
					shipScnr.nextLine();
					System.out.println("Please enter a valid number");
				}
				roomInterior = shipScnr.nextInt();
			} else {
				roomInterior = shipScnr.nextInt();
			}

			System.out.println("Is the ship in Service? Enter true or false.");
			if (!shipScnr.hasNextBoolean()) {
				while (!shipScnr.hasNextBoolean()) {
					shipScnr.nextLine();
					shipScnr.next();
					System.out.println("Invalid Input. Please enter true or false.");
				}
				inService = shipScnr.nextBoolean();
			} else {
				inService = shipScnr.nextBoolean();
			}

			// Adds ship to the list of ships once all input has been validated.
			add(shipName, roomBalcony, roomOceanView, roomSuite, roomInterior, inService);

			// Checks if the user has more ships to enter.
			System.out.println("Continue? Y/N");
			quit = shipScnr.next();
		} while (!quit.equalsIgnoreCase("n"));
	}

	// Edit an existing ship
	public static void editShip() {

		// This method does not need to be completed
		System.out.println("The \"Edit Ship\" feature is not yet implemented.");

	}

	// Add a New Cruise
	public static void addCruise() {
		String cruiseName;
		String cruiseShipName;
		String departurePort;
		String destination;
		String returnPort;
		boolean newCruise;
		boolean validShip;
		boolean shipExists;
		String quit = "Y";

		// Do while loop allows user to add multiple ships until they're done.
		do {
			Scanner cruiseScnr = new Scanner(System.in);

			// Validates then adds a ship name. Loops until a name is entered
			System.out.println("Please enter the cruise name: ");
			do {
				newCruise = true;
				cruiseName = cruiseScnr.nextLine();
				while (cruiseName.isEmpty()) {
					System.out.println("No name was entered, please enter a name.");
					cruiseName = cruiseScnr.nextLine();
				}
				for (Cruise eachCruise : cruiseList) {
					if (cruiseName.equalsIgnoreCase(eachCruise.getCruiseName())) {
						System.out.println("Cruise already exists in the system. Please try again.");
						newCruise = false;
					}
				}
			} while (!newCruise);

			// Validates and adds a ship to the cruise.
			System.out.println("Please enter the name of the cruise Ship. ");
			do {
				shipExists = false;
				validShip = false;
				cruiseShipName = cruiseScnr.nextLine();
				// While loop ensures input is entered.
				while (cruiseShipName.isEmpty()) {
					System.out.println("No name was entered, please enter a name.");
					cruiseShipName = cruiseScnr.nextLine();
				}
				/*
				 * For loop checks if the ship exists, is in service, and if it's assigned to
				 * another cruise.
				 */
				for (Ship eachShip : shipList) {
					if (cruiseShipName.equalsIgnoreCase(eachShip.getShipName())) {
						shipExists = true;
						validShip = true;
						if (!eachShip.getInService()) {
							System.out.println("Ship is out of service, please select another.");
							validShip = false;
						}
						for (Cruise eachCruise : cruiseList) {
							if (cruiseShipName.equalsIgnoreCase(eachCruise.getCruiseShipName())) {
								System.out.println("Ship is assigned to another cruise, please select another.");
								validShip = false;
							}
						}
					}
				}
				if (!shipExists) {
					System.out.println("Ship name does not exist, please try again.");
				}
			} while (!validShip);

			System.out.println("Please enter the name of the departure port. ");
			departurePort = cruiseScnr.nextLine();
			while (departurePort.isEmpty()) {
				System.out.println("No departure port was entered, please try again.");
				departurePort = cruiseScnr.nextLine();
			}

			System.out.println("Please enter the destination of the cruise.");
			destination = cruiseScnr.nextLine();
			while (destination.isEmpty()) {
				System.out.println("No destination was entered, please try again.");
				destination = cruiseScnr.nextLine();
			}

			System.out.println("Please enter the return port of the cruise Ship: ");
			returnPort = cruiseScnr.nextLine();
			while (returnPort.isEmpty()) {
				System.out.println("No return port was entered, please try again.");
				returnPort = cruiseScnr.nextLine();
			}

			// Adds cruise to the list of cruises once all input has been validated.
			Cruise newCruise2 = new Cruise(cruiseName, cruiseShipName, departurePort, destination, returnPort);
			cruiseList.add(newCruise2);
			// Checks if the user has more cruises to enter.
			System.out.println("Continue? Y/N");
			quit = cruiseScnr.next();
		} while (!quit.equalsIgnoreCase("n"));
	}

	// Edit an existing cruise
	public static void editCruise() {

		// This method does not need to be completed
		System.out.println("The \"Edit Cruise\" feature is not yet implemented.");

	}

	// Add a New Passenger
	public static void addPassenger() {

		Scanner newPassengerInput = new Scanner(System.in);
		System.out.println("Enter the new passenger's name: ");
		String newPassengerName = newPassengerInput.nextLine();

		// ensure new passenger name does not already exist
		for (Passenger eachPassenger : passengerList) {
			if (eachPassenger.getPassengerName().equalsIgnoreCase(newPassengerName)) {
				System.out.println("That passenger is already in the system. Exiting to menu...");
				return; // quits addPassenger() method processing
			}
		}

		// get cruise name for passenger
		System.out.println("Enter cruise name: ");
		String newCruiseName = newPassengerInput.nextLine();

		// ensure cruise exists
		for (Cruise eachCruise : cruiseList) {
			if (eachCruise.getCruiseName().equalsIgnoreCase(newCruiseName)) {
				// cruise does exist
			} else {
				System.out.println("That cruise does not exist in the system. Exiting to menu...");
				return; // quits addPassenger() method processing
			}
		}

		// get room type
		System.out.println("Enter Room Type (BAL, OV, STE, or INT: ");
		String room = newPassengerInput.nextLine();
		// validate room type
		if ((room.equalsIgnoreCase("BAL")) || (room.equalsIgnoreCase("OV")) || (room.equalsIgnoreCase("STE"))
				|| (room.equalsIgnoreCase("INT"))) {
			// validation passed - add passenger
			Passenger newPassenger = new Passenger(newPassengerName, newCruiseName, room.toUpperCase());
			passengerList.add(newPassenger);
		} else {
			System.out.println("Invalid input. Exiting to menu...");
			return; // quits addPassenger() method processing
		}
	}

	// Edit an existing passenger
	public static void editPassenger() {

		// This method does not need to be completed
		System.out.println("The \"Edit Passenger\" feature is not yet implemented.");

	}

	// Method to check if input is a number
	public static boolean isANumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i)) == false)
				return false;
		}
		return true;
	}

}
