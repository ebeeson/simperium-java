package simperium;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class SimperiumApplication {
	final SimperiumConfiguration configuration;
	final String appId;

	public SimperiumApplication(String appId) {
		this(appId, null);
	}

	public SimperiumApplication(String appId, SimperiumConfiguration configuration) {
		this.configuration = (configuration != null ? configuration : new DefaultSimperiumConfiguration());
		this.appId = appId;
	}

	public AuthAccess getAuthAccess(String apiKey) {
		return new AuthAccess(this, apiKey);
	}

	public AdminAccess getAdminAccess(String adminKey) {
		return new AdminAccess(this, adminKey);
	}

	public UserAccess getUserAccess(String accessToken) {
		return new UserAccess(this, accessToken);
	}

	public void shutdown() {
		configuration.shutdown();
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("SimperiumApplication");
		sb.append("{").append(appId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
