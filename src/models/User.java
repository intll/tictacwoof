package models;

public class User {

	private String id;
	private String email;
	private String username;
	private Integer corgions;
	private Integer treats;
	private String inventory;

	public User(String id, String email, String username, Integer corgions, Integer treats, String inventory) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.corgions = corgions;
		this.treats = treats;
		this.inventory = inventory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCorgions() {
		return corgions;
	}

	public void setCorgions(Integer corgions) {
		this.corgions = corgions;
	}

	public Integer getTreats() {
		return treats;
	}

	public void setTreats(Integer treats) {
		this.treats = treats;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

}
