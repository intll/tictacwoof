package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.User;

public class QueryManager {

	private Connector conn = Connector.getConnection();
	private ResultSet rs;
	private PreparedStatement ps;

	// Format: [UserID] - [FullName] -[UserEmail] - [Username] - [UserPassword] -
	// [Corgions] - [Treats] - [Inventory]
	public User searchUser(String email, String password) {
		String query = "SELECT * FROM users WHERE UserEmail = ?";
		ps = conn.prepareStatement(query);

		try {
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				rs.next();
				String id = rs.getString(1);
				String username = rs.getString(3);
				String checkPassword = rs.getString(4);
				Integer corgions = rs.getInt(5);
				Integer treats = rs.getInt(6);
				String inventory = rs.getString(7);

				if (checkPassword.equals(password))
					return new User(id, email, username, corgions, treats, inventory);
				else
					return new User("WPASS", "", "", 0, 0, "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[!] Fatal SQL Error! Exiting system.");
			System.exit(1);
		}

		System.out.println("[!] Wrong username or password!");
		return null;
	}

	//BICOLOR-TRICOLOR-BEAGLE-PUG-HUSKY-SAMOYED, 0: False, 1: True
	public Boolean insertUser(String email, String username, String password) {
		User searchResults = searchUser(email, password);
		if (searchResults == null) {
			String newID = newUserID();
			String query = "INSERT INTO users VALUES (?, ?, ?, ?, 5, 200, '100000')";
			ps = conn.prepareStatement(query);
			try {
				ps.setString(1, newID);
				ps.setString(2, email);
				ps.setString(3, username);
				ps.setString(4, password);
				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("[!] Fatal SQL Error! Exiting system.");
				System.exit(1);
			}

			return true;
		}

		return false;
	}

	public String newUserID() {
		String query = "SELECT UserID FROM users ORDER BY UserID DESC LIMIT 1";

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			if (!rs.isBeforeFirst())
				return "US001";

			rs.next();
			return String.format("US%03d", Integer.parseInt(rs.getString(1).substring(2)) + 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateCurrency(String userID, Integer corgions, Integer treatos) {
		String query = "UPDATE users SET Corgions = ?, Treats = ? WHERE UserID = ?";

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, corgions);
			ps.setInt(2, treatos);
			ps.setString(3, userID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateInventory(String userID, String inventory) {
		String query = "UPDATE users SET Inventory = ? WHERE UserID = ?";

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, inventory);
			ps.setString(2, userID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
