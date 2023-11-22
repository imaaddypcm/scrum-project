/**
 * Stores details for a given room type.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
package backend;

public class RoomType {
	private int id;
	private String name;
	private String description;
	private String rules;
	private int numberOfBeds;
	private int price;

	/*
	Single
	Double
	Standard
	Luxury
	Family
	Pet Friendly
	Smoking
	Executive
	Studio/Suite
	 */

	public RoomType(int id, String name, String description, String rules, int numberOfBeds, int price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.rules = rules;
		this.numberOfBeds = numberOfBeds;
		this.price = price;
	}
	/**
	 * Get a RoomType object's id.
	 * @return Returns id represented as an integer.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get a RoomType object's name.
	 * @return Returns name represented as a string.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get a RoomType object's description.
	 * @return Returns description represented as a string.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get a RoomType object's rules.
	 * @return Returns rules represented as a string.
	 */
	public String getRules() {
		return rules;
	}

	/**
	 * Get a RoomType object's number of beds.
	 * @return Returns number of beds represented as an integer.
	 */
	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	/**
	 * Get a RoomType object's price.
	 * @return Returns price represented as a int.
	 */
	public int getPrice() {
		return price;
	}
}
