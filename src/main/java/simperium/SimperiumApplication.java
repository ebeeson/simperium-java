package simperium;

import com.google.gson.Gson;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class SimperiumApplication {
	public SimperiumApplication(String appId) {
		throw new UnsupportedOperationException();
	}

	public SimperiumApplication(String appId, Gson gson) {
		throw new UnsupportedOperationException();
	}

	public AuthAccess getAuthAccess(String apiKey) {
		throw new UnsupportedOperationException();
	}

	public AdminAccess getAdminAccess(String adminKey) {
		throw new UnsupportedOperationException();
	}

	public UserAccess getUserAccess(String accessToken) {
		throw new UnsupportedOperationException();
	}
}
