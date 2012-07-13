package simperium;

import com.google.gson.JsonObject;

/**
 * User specific operations available with a user's <code>access token</code>.
 *
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class UserAccess extends SimperiumBase {
	UserAccess(SimperiumApplication app, String accessToken) {
		super(app, new AccessTokenHeader(accessToken));
	}

	public SimperiumBucket<JsonObject> getBucket(String name) {
		return new SimperiumBucket<JsonObject>(this, name, JsonObject.class);
	}

	public <T> SimperiumBucket<T> getBucket(String name, Class<T> clazz) {
		return new SimperiumBucket<T>(this, name, clazz);
	}

	public BucketChanges getBucketChanges(final String name, String cv) {
		return new BucketChanges(this, cv) {
			protected String nextUrl(String cv) {
				return urlBucketChanges(name, cv);
			}
		};
	}
}
