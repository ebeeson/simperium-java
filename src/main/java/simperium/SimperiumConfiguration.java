package simperium;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public interface SimperiumConfiguration {
	void shutdown();

	HttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException;

	<T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException;

	<T> T fromJson(HttpResponse response, Type type) throws IOException;

	<T> T fromJson(HttpResponse response, Class<T> clazz) throws IOException;

	String toJson(Object o);
}
