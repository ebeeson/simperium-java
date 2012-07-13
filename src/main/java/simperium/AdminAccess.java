package simperium;

import com.google.gson.JsonObject;

import java.io.IOException;

/**
 * Administrative operations available with the application's <code>admin key</code>.
 *
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class AdminAccess extends SimperiumBase {
	AdminAccess(SimperiumApplication app, String adminKey) {
		super(app, new AccessTokenHeader(adminKey));
	}

	/**
	 * Change password for a user without current password. Requires an API key with admin privileges.
	 *
	 * POST https://auth.simperium.com/1/{ app_id }/reset_password/
	 *
	 * @param username Usernames currently must be a valid email address
	 * @param password New password (optional)
	 * @see <a href="https://simperium.com/docs/reference/http/#reset_password">https://simperium.com/docs/reference/http/#reset_password</a>
	 */
	public boolean resetPassword(String username, String password) throws IOException {
		return execute(httpPost(urlAuthDelete(), new UserAuthBody(username, null, null, password)), StatusResponse.class).isSuccess();
	}

	/**
	 * Deletes a user and all user data. Requires an API key with admin privileges.
	 *
	 * POST https://auth.simperium.com/1/{ app_id }/delete/
	 *
	 * @param username Usernames currently must be a valid email address
	 * @see <a href="https://simperium.com/docs/reference/http/#delete">https://simperium.com/docs/reference/http/#delete</a>
	 */
	public boolean delete(String username) throws IOException {
		return execute(httpPost(urlAuthDelete(), new UserAuthBody(username)), StatusResponse.class).isSuccess();
	}

	public SimperiumBucket<JsonObject> getBucket(String name, String userId) {
		return new SimperiumBucket<JsonObject>(this, name, JsonObject.class, userId);
	}

	public <T> SimperiumBucket<T> getBucket(String name, String userId, Class<T> clazz) {
		return new SimperiumBucket<T>(this, name, clazz, userId);
	}

	public BucketChanges getBucketChanges(final String bucketName, final boolean data, final boolean mostRecent,
										  final boolean username, String cv) throws IOException {
		return new BucketChanges(this, cv) {
			protected String nextUrl(String cv) {
				return urlBucketAll(bucketName, cv, data, mostRecent, username);
			}
		};
	}
}
