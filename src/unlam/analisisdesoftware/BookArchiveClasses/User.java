package unlam.analisisdesoftware.BookArchiveClasses;

public class User implements Comparable<User>{
	private String userName;
	private String password;
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	@Override
	public int compareTo(User user) {
		return user.compareTo(user);
	}

	@Override
	public boolean equals(Object user) {
		// TODO Auto-generated method stub
		return this==user || (user instanceof User && userName.equals(((User)user).userName));
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
