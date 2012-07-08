package simperium;

import com.google.gson.Gson;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class UserAccess {
	public UserAccess(Gson gson, String appId, String accessToken) {
		throw new UnsupportedOperationException("simperium.UserAccess#UserAccess[gson, appId, accessToken]");
	}

	public SimperiumBucket<String> getBucket(String name) {
		throw new UnsupportedOperationException("simperium.UserAccess#getBucket[name]");
	}

	public <T> SimperiumBucket<T> getBucket(String name, Class<T> clazz) {
		throw new UnsupportedOperationException("simperium.UserAccess#getBucket[name, clazz]");
	}
}
