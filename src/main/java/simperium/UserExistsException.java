package simperium;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class UserExistsException extends Exception {
	private final String username;

	public UserExistsException(String username) {
		super("Username already exists: " + username);
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
