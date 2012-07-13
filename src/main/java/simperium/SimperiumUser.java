package simperium;

import com.google.gson.annotations.SerializedName;

/**
 * Response from a {@link AuthAccess#create(String, String)} or {@link AuthAccess#authorize(String, String)} request.
 *
 * {"username": "test@test.com", "access_token": "84f27d20f93b414f8b7bc3441f87c9e1", "userid": "f5067cc81c9c26dcdca468f0cdf60508"}
 *
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class SimperiumUser {
	@SerializedName("userid")
	private String id;
	private String username;
	@SerializedName("access_token")
	private String accessToken;

	/**
	 * Only here for deserialization.
	 */
	private SimperiumUser() {
	}

	/**
	 * @return A unique id that won't change if user changes their username
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return Usernames currently must be a valid email address
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return Use token to access data for this user
	 */
	public String getAccessToken() {
		return accessToken;
	}

	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof SimperiumUser)) return false;

		SimperiumUser that = (SimperiumUser) o;

		return !(accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) &&
				!(id != null ? !id.equals(that.id) : that.id != null) &&
				!(username != null ? !username.equals(that.username) : that.username != null);

	}

	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
		return result;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("SimperiumUser");
		sb.append("{id='").append(id).append('\'');
		sb.append(", username='").append(username).append('\'');
		sb.append(", accessToken='").append(accessToken).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
