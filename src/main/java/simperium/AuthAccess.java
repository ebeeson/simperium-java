package simperium;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;

/**
 * User authorization operations available with the application's <code>api key</code>.
 *
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class AuthAccess extends SimperiumBase {
	AuthAccess(SimperiumApplication app, String apiKey) {
		super(app, new ApiKeyHeader(apiKey));
	}

	/**
	 * Creates a user if they don't already exist.
	 *
	 * POST https://auth.simperium.com/1/{ app_id }/create/
	 *
	 * @param username Usernames currently must be a valid email address
	 * @param password Password for the user
	 * @throws UserExistsException If the given username has already been created (HTTP 409 Conflict)
	 * @throws IOException
	 * @see <a href="https://simperium.com/docs/reference/http/#create">https://simperium.com/docs/reference/http/#create</a>
	 */
	public SimperiumUser create(String username, String password) throws UserExistsException, IOException {
		HttpResponse response = execute(httpPost(urlAuthCreate(), new UserAuthBody(username, password)));
		if(response.getStatusLine().getStatusCode() == 409) { // conflict
			throw new UserExistsException(username);
		} else {
			return fromJson(response, SimperiumUser.class);
		}
	}

	/**
	 * Gets a new access token for a user.
	 *
	 * POST https://auth.simperium.com/1/{ app_id }/authorize/
	 *
	 * @param username Usernames currently must be a valid email address
	 * @param password Password for the user
	 * @throws IOException
	 * @see <a href="https://simperium.com/docs/reference/http/#authorize">https://simperium.com/docs/reference/http/#authorize</a>
	 */
	public SimperiumUser authorize(String username, String password) throws IOException {
		return execute(httpPost(urlAuthAuthorize(), new UserAuthBody(username, password)), new ResponseHandler<SimperiumUser>() {
			public SimperiumUser handleResponse(HttpResponse response) throws IOException {
				return response.getStatusLine().getStatusCode() == 401 ? null : fromJson(response, SimperiumUser.class);
			}
		});
	}

	/**
	 * Updates username or password for a user. Requires both current username and password.
	 *
	 * POST https://auth.simperium.com/1/{ app_id }/update/
	 *
	 * @param username Usernames currently must be a valid email address
	 * @param password Password for the user
	 * @param newUsername New username (optional)
	 * @param newPassword New password (optional)
	 */
	public boolean update(String username, String password, String newUsername, String newPassword) throws IOException {
		return execute(httpPost(urlAuthUpdate(), new UserAuthBody(username, password, newUsername, newPassword)), StatusResponse.class).isSuccess();
	}
}
