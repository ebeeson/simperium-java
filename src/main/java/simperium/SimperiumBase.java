package simperium;

import com.damnhandy.uri.template.UriTemplate;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
@SuppressWarnings({"UnusedDeclaration", "DuplicateThrows"})
abstract class SimperiumBase {
	private static final String URL_AUTH = "https://auth.simperium.com/1/{app_id}/";
	private static final String URL_API = "https://api.simperium.com/1/{app_id}/{bucket_name}/";

	private static final String TEMPLATE_AUTH_CREATE = URL_AUTH + "create/";
	private static final String TEMPLATE_AUTH_AUTHORIZE = URL_AUTH + "authorize/";
	private static final String TEMPLATE_AUTH_UPDATE = URL_AUTH + "update/";
	private static final String TEMPLATE_AUTH_RESET_PASSWORD = URL_AUTH + "reset_password/";
	private static final String TEMPLATE_AUTH_DELETE = URL_AUTH + "delete/";

	private static final String TEMPLATE_OBJECT = URL_API + "i{/user}{/id}{/version*}{?clientid,ccid,response,replace}";
	private static final String TEMPLATE_BUCKET_INDEX = URL_API + "index{?limit,mark,since,data}";
	private static final String TEMPLATE_BUCKET_CHANGES = URL_API + "changes{?cv}";
	private static final String TEMPLATE_BUCKET_ALL = URL_API + "all{?cv,data,username,most_recent}";

	protected static final String HEADER_API_KEY = "X-Simperium-API-Key";
	protected static final String HEADER_TOKEN = "X-Simperium-Token";
	protected static final String HEADER_USER = "X-Simperium-User";
	protected static final String HEADER_VERSION = "X-Simperium-Version";

	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

	protected final SimperiumApplication app;

	private final Header[] headers;

	protected SimperiumBase(SimperiumApplication app, Header... headers) {
		this.app = app;
		this.headers = headers;
	}

	protected SimperiumBase(SimperiumBase base) {
		this(base.app, base.headers);
	}


	//region URL builders
	protected String urlAuthCreate() {
		return expandUrl(TEMPLATE_AUTH_CREATE, new LinkedHashMap<String, Object>() {{
			put("app_id", app.appId);
		}});
	}

	protected String urlAuthAuthorize() {
		return expandUrl(TEMPLATE_AUTH_AUTHORIZE, new LinkedHashMap<String, Object>() {{
			put("app_id", app.appId);
		}});
	}

	protected String urlAuthUpdate() {
		return expandUrl(TEMPLATE_AUTH_UPDATE, new LinkedHashMap<String, Object>() {{
			put("app_id", app.appId);
		}});
	}

	protected String urlAuthResetPassword() {
		return expandUrl(TEMPLATE_AUTH_RESET_PASSWORD, new LinkedHashMap<String, Object>() {{
			put("app_id", app.appId);
		}});
	}

	protected String urlAuthDelete() {
		return expandUrl(TEMPLATE_AUTH_DELETE, new LinkedHashMap<String, Object>() {{
			put("app_id", app.appId);
		}});
	}

	protected String urlBucket(final String bucketName) {
		return expandUrl(TEMPLATE_BUCKET_INDEX, new LinkedHashMap<String, Object>() {{
			put("app_id", app.appId);
			put("bucket_name", bucketName);
			put("data", "1");
		}});
	}

	protected String urlObject(final String bucketName, final String user, final String id) {
		return expandUrl(TEMPLATE_OBJECT, new LinkedHashMap<String, Object>() {{
			put("app_id", app.appId);
			put("bucket_name", bucketName);
			put("user", user);
			put("id", id);
		}});
	}

	protected String urlObject(final String bucketName, final String user, final String id, final int version) {
		return expandUrl(TEMPLATE_OBJECT, new LinkedHashMap<String, Object>() {{
			put("app_id", app.appId);
			put("bucket_name", bucketName);
			put("user", user);
			put("id", id);
			put("version", Arrays.asList("v", version));
		}});
	}

	protected String urlBucketChanges(final String bucketName, final String cv) {
		return expandUrl(TEMPLATE_BUCKET_CHANGES, new LinkedHashMap<String, Object>() {{
			put("app_id", app.appId);
			put("bucket_name", bucketName);
			put("cv", cv);
		}});
	}

	protected String urlBucketAll(final String bucketName, final String cv, final boolean data, final boolean mostRecent, final boolean username) {
		return expandUrl(TEMPLATE_BUCKET_ALL, new LinkedHashMap<String, Object>() {{
			put("app_id", app.appId);
			put("bucket_name", bucketName);
			if(cv != null) put("cv", cv);
			if(username) put("username", "1");
			if(data) put("data", "1");
			if(mostRecent) put("most_recent", "1");
		}});
	}


	private String expandUrl(String uri, Map<String, Object> parameters) {
		String expanded = UriTemplate.fromExpression(uri).expand(parameters);
		LOGGER.info("{} [{}]{}", new Object[]{expanded, uri, parameters});
		return expanded;
	}
	//endregion


	//region Create HttpMethods
	protected HttpGet httpGet(String uri) {
		HttpGet method = new HttpGet(uri);
		for(Header header : headers) {
			method.addHeader(header);
		}
		return method;
	}

	protected HttpPost httpPost(String uri, Object data) {
		HttpPost method = new HttpPost(uri);
		for(Header header : headers) {
			method.addHeader(header);
		}
		method.setEntity(new StringEntity(toJson(data), ContentType.APPLICATION_JSON));
		return method;
	}

	protected HttpDelete httpDelete(String uri) {
		HttpDelete method = new HttpDelete(uri);
		for(Header header : headers) {
			method.addHeader(header);
		}
		return method;
	}
	//endregion


	//region Convenience delegates to SimperiumConfiguration
	protected <T> T fromJson(HttpResponse response, Type type) throws IOException {
		return app.configuration.fromJson(response, type);
	}

	protected <T> T fromJson(HttpResponse response, Class<T> clazz) throws IOException {
		return app.configuration.fromJson(response, clazz);
	}

	protected String toJson(Object o) {
		return app.configuration.toJson(o);
	}

	protected HttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException {
		return app.configuration.execute(request);
	}

	protected <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
		return app.configuration.execute(request, responseHandler);
	}

	protected <T> T execute(HttpUriRequest request, Class<T> clazz) throws IOException, ClientProtocolException {
		return app.configuration.execute(request, new GsonResponseHandler<T>(clazz));
	}

	protected <T> T execute(HttpUriRequest request, Type type) throws IOException, ClientProtocolException {
		return app.configuration.execute(request, new GsonResponseHandler<T>(type));
	}
	//endregion


	private class GsonResponseHandler<T> implements ResponseHandler<T> {
		private final Type type;

		public GsonResponseHandler(Type type) {
			this.type = type;
		}

		public T handleResponse(HttpResponse response) throws IOException {
			return fromJson(response, type);
		}
	}

	protected class VersionedObjectHandler<T> implements ResponseHandler<VersionedObject<T>> {
		private final String id;
		private final Class<T> clazz;

		public VersionedObjectHandler(String id, Class<T> clazz) {
			this.id = id;
			this.clazz = clazz;
		}

		public VersionedObject<T> handleResponse(HttpResponse response) throws IOException {
			return new VersionedObject<T>(response.getFirstHeader(HEADER_VERSION).getValue(), id, fromJson(response, clazz));
		}
	}

	protected static class AccessTokenHeader extends BasicHeader {
		public AccessTokenHeader(String accessToken) {
			super(HEADER_TOKEN, accessToken);
		}
	}

	protected static class ApiKeyHeader extends BasicHeader {
		public ApiKeyHeader(String apiKey) {
			super(HEADER_API_KEY, apiKey);
		}
	}

	protected static class UserAuthBody {
		private final String username;
		private final String password;
		private final String new_username;
		private final String new_password;

		public UserAuthBody(String username) {
			this(username, null, null, null);
		}

		public UserAuthBody(String username, String password) {
			this(username, password, null, null);
		}

		public UserAuthBody(String username, String password, String newUsername, String newPassword) {
			this.username = username;
			this.password = password;
			this.new_username = newUsername;
			this.new_password = newPassword;
		}
	}

	protected static class StatusResponse {
		private String status;

		public boolean isSuccess() {
			return "success".equals(status);
		}
	}
}
