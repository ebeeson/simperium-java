package simperium;

import com.google.gson.Gson;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class AdminAccess {
	public AdminAccess(Gson gson, String appId, String adminKey) {
		throw new UnsupportedOperationException("simperium.AdminAccess#AdminAccess[gson, appId, adminKey]");
	}

	public void resetPassword(String username, String password) {
		throw new UnsupportedOperationException("simperium.AdminAccess#resetPassword[username, password]");
	}

	public void delete(String username) {
		throw new UnsupportedOperationException("simperium.AdminAccess#delete[username]");
	}

	public SimperiumBucket<String> getBucket(String name, String userId) {
		throw new UnsupportedOperationException("simperium.AdminAccess#getBucket[name, userId]");
	}

	public <T> SimperiumBucket<T> getBucket(String name, String userId, Class<T> clazz) {
		throw new UnsupportedOperationException("simperium.AdminAccess#getBucket[name, userId, clazz]");
	}
}
