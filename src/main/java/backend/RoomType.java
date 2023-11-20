package backend;

/**
 *  Name:
    Date: ??/??/2023
    Programmers:
    Description:
    Functions:
    Data Structures:
    Algorithms:
*/
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

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getRules() {
		return rules;
	}

	public int getPrice(){
		return price;
	}
}
