package backend;

public class RoomType {
	private int id;
	private String name;
	private String description;
	private String rules;

	public RoomType(int id, String name, String description, String rules) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.rules = rules;
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
}


