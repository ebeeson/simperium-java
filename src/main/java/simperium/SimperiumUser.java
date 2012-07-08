package simperium;

import com.google.gson.annotations.SerializedName;

/**
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

	private SimperiumUser() {
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

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
