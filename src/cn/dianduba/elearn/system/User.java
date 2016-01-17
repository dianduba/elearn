package cn.dianduba.elearn.system;

public class User {
	private int id;
	private String username;
	private String password;
	private int balanceHits;
	private int usedHits;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBalanceHits() {
		return balanceHits;
	}

	public void setBalanceHits(int balanceHits) {
		this.balanceHits = balanceHits;
	}

	public int getUsedHits() {
		return usedHits;
	}

	public void setUsedHits(int usedHits) {
		this.usedHits = usedHits;
	}
	
	
}
