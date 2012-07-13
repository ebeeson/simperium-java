package simperium;

import com.google.gson.*;
import com.google.gson.internal.bind.TypeAdapters;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class DefaultSimperiumConfiguration implements SimperiumConfiguration {
	private final Gson gson;
	private final HttpClient client;

	public DefaultSimperiumConfiguration() {
		this(null, null);
	}

	public DefaultSimperiumConfiguration(Gson gson) {
		this(gson, null);
	}

	public DefaultSimperiumConfiguration(HttpClient client) {
		this(null, client);
	}

	public DefaultSimperiumConfiguration(Gson gson, HttpClient client) {
		this.gson = (gson != null ? gson : defaultGsonBuilder().create());
		this.client = (client != null ? client : new DefaultHttpClient(new PoolingClientConnectionManager()));
	}

	public static GsonBuilder defaultGsonBuilder() {
		return new GsonBuilder()
				.registerTypeAdapter(JsonObject.class, TypeAdapters.JSON_ELEMENT)
				.registerTypeAdapter(JsonArray.class, TypeAdapters.JSON_ELEMENT)
				.registerTypeAdapter(JsonPrimitive.class, TypeAdapters.JSON_ELEMENT)
				.registerTypeAdapter(JsonNull.class, TypeAdapters.JSON_ELEMENT)
				.registerTypeAdapter(JsonElement.class, TypeAdapters.JSON_ELEMENT);
	}

	public void shutdown() {
		client.getConnectionManager().shutdown();
	}

	public HttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException {
		return client.execute(request);
	}

	public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
		return client.execute(request, responseHandler);
	}

	public <T> T fromJson(HttpResponse response, Type type) throws IOException {
		return gson.fromJson(EntityUtils.toString(response.getEntity()), type);
	}

	public <T> T fromJson(HttpResponse response, Class<T> clazz) throws IOException {
		return gson.fromJson(EntityUtils.toString(response.getEntity()), clazz);
	}

	public String toJson(Object o) {
		return gson.toJson(o);
	}
}
