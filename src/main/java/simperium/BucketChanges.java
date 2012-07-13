package simperium;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public abstract class BucketChanges extends SimperiumBase {
	private final Queue<JsonObject> queue = new LinkedList<JsonObject>();
	private String cv;

	BucketChanges(SimperiumBase access, String cv) {
		super(access);
		this.cv = cv;
	}

	protected abstract String nextUrl(String cv);

	public JsonObject nextChange() throws IOException {
		while(queue.isEmpty()) {
			HttpResponse response = execute(httpGet(nextUrl(cv)));
			if(response.getStatusLine().getStatusCode() == 200) {
				for(JsonElement element : fromJson(response, JsonArray.class)) {
					JsonObject json = element.getAsJsonObject();
					cv = json.getAsJsonPrimitive("cv").getAsString();
					queue.add(json);
				}
			} else {
				EntityUtils.consumeQuietly(response.getEntity());
			}
		}

		return queue.poll();
	}
}
