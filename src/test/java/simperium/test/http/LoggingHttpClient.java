package simperium.test.http;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Erik Beeson
 */
public class LoggingHttpClient implements HttpClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingHttpClient.class);

	private void log(HttpUriRequest request) {
		if(request instanceof HttpEntityEnclosingRequestBase) {
			LOGGER.info("{} {} {}", new Object[]{request.getMethod(), request.getURI(), getEntity((HttpEntityEnclosingRequestBase) request)});
		} else {
			LOGGER.info("{} {}", request.getMethod(), request.getURI());
		}
		for(Header header : request.getAllHeaders()) {
			LOGGER.info("{}", header);
		}
	}

	public static String getEntity(HttpEntityEnclosingRequestBase request) {
		try {
			return EntityUtils.toString(request.getEntity());
		} catch(IOException e) {
			throw new RuntimeException(e); // don't want something downstream to try to deal with this
		}
	}

	public HttpResponse execute(HttpUriRequest request) throws IOException {
		log(request);
		throw new InterruptedHttpRequest(request);
	}

	public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
		log(request);
		throw new InterruptedHttpRequest(request);
	}

	public HttpParams getParams() {
		throw new UnsupportedOperationException(getClass().getName() + "#getParams[]");
	}

	public ClientConnectionManager getConnectionManager() {
		throw new UnsupportedOperationException(getClass().getName() + "#getConnectionManager[]");
	}

	public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException {
		throw new UnsupportedOperationException(getClass().getName() + "#execute[request, context]");
	}

	public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException {
		throw new UnsupportedOperationException(getClass().getName() + "#execute[target, request]");
	}

	public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException {
		throw new UnsupportedOperationException(getClass().getName() + "#execute[target, request, context]");
	}

	public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException {
		throw new UnsupportedOperationException(getClass().getName() + "#execute[request, responseHandler, context]");
	}

	public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
		throw new UnsupportedOperationException(getClass().getName() + "#execute[target, request, responseHandler]");
	}

	public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException {
		throw new UnsupportedOperationException(getClass().getName() + "#execute[target, request, responseHandler, context]");
	}

	public static class InterruptedHttpRequest extends RuntimeException {
		private final HttpUriRequest request;

		public InterruptedHttpRequest(HttpUriRequest request) {
			super(String.valueOf(request.getRequestLine()));
			this.request = request;
		}

		public HttpUriRequest getRequest() {
			return request;
		}
	}

}
