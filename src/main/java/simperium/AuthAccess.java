package simperium;

import com.google.gson.Gson;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class AuthAccess {
	public AuthAccess(Gson gson, String appId, String apiKey) {
		throw new UnsupportedOperationException("simperium.AuthAccess#AuthAccess[gson, appId, apiKey]");
	}

	public SimperiumUser create(String username, String password) throws UserExistsException {
		throw new UnsupportedOperationException("simperium.AuthAccess#create[username, password]");
	}

	public SimperiumUser authorize(String username, String password) {
		throw new UnsupportedOperationException("simperium.AuthAccess#authorize[username, password]");
	}

	public void update(String username, String password, String newUsername, String newPassword) {
		throw new UnsupportedOperationException("simperium.AuthAccess#update[username, password, newUsername, newPassword]");
	}
}
