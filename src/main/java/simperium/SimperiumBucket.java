package simperium;

import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.lang.reflect.Type;


/**
 * Operations related to a particular bucket for a particular user.
 *
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class SimperiumBucket<T> extends SimperiumBase {
	private final Type BUCKET_INDEX_TYPE = new TypeToken<BucketIndex<T>>() {
	}.getType();

	private static final ResponseHandler<Integer> VERSION_HANDLER = new ResponseHandler<Integer>() {
		public Integer handleResponse(HttpResponse response) throws IOException {
			return Integer.parseInt(response.getFirstHeader(HEADER_VERSION).getValue(), 10);
		}
	};

	private final String name;
	private final Class<T> clazz;
	private final String userId;

	SimperiumBucket(UserAccess user, String name, Class<T> clazz) {
		super(user);

		this.name = name;
		this.clazz = clazz;
		this.userId = null;
	}

	SimperiumBucket(AdminAccess admin, String name, Class<T> clazz, String userId) {
		super(admin);

		this.name = name;
		this.clazz = clazz;
		this.userId = userId;
	}

	public String getBucketName() {
		return name;
	}

	/**
	 * Retrieves a page of objects for a bucket
	 *
	 * GET https://api.simperium.com/1/{ app_id }/{ bucket_name }/index
	 *
	 * @throws IOException
	 */
	public BucketIndex<T> list() throws IOException {
		HttpGet request = httpGet(urlBucket(name));
		if(userId != null) {
			request.addHeader(HEADER_USER, userId);
		}
		return execute(request, BUCKET_INDEX_TYPE);
	}

	/**
	 * Retrieves object data from the bucket
	 *
	 * GET https://api.simperium.com/1/{ app_id }/{ bucket_name }/i/{ object id }
	 *
	 * @param id The id of the object to retrieve

	 * @throws IOException
	 */
	public VersionedObject<T> getObject(String id) throws IOException {
		return execute(
				httpGet(urlObject(name, userId, id)),
				new VersionedObjectHandler<T>(id, clazz)
		);
	}

	/**
	 * Retrieves given version of object data from the bucket
	 *
	 * GET https://api.simperium.com/1/{ app_id }/{ bucket_name }/i/{ object id }/v/{ version }
	 *
	 * @param id The id of the object to retrieve
	 * @param version A specific object version to retrieve
	 * @throws IOException
	 */
	public VersionedObject<T> getObject(String id, int version) throws IOException {
		return execute(
				httpGet(urlObject(name, userId, id, version)),
				new VersionedObjectHandler<T>(id, clazz)
		);
	}

	/**
	 *
	 * @param id The id of the object to retrieve
	 * @param data
	 * @throws IOException
	 */
	public int updateObject(String id, T data) throws IOException {
		return execute(
				httpPost(urlObject(name, userId, id), data),
				VERSION_HANDLER
		);
	}

	/**
	 *
	 * @param id The id of the object to retrieve
	 * @param data
	 * @param version A specific object version to modify
	 * @throws IOException
	 */
	public int updateObject(String id, T data, int version) throws IOException {
		return execute(
				httpPost(urlObject(name, userId, id, version), data),
				VERSION_HANDLER
		);
	}

	/**
	 *
	 * @param id The id of the object to retrieve
	 * @throws IOException
	 */
	public int deleteObject(String id) throws IOException {
		return execute(
				httpDelete(urlObject(name, userId, id)),
				VERSION_HANDLER
		);
	}

	/**
	 *
	 * @param id The id of the object to retrieve
	 * @param version A specific object version to delete, if you suppply this, delete will only succeed if the current object version matches this parameter
	 * @throws IOException
	 */
	public int deleteObject(String id, int version) throws IOException {
		return execute(
				httpDelete(urlObject(name, userId, id, version)),
				VERSION_HANDLER
		);
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("SimperiumBucket{").append(name).append(", ").append(clazz.getName());
		if(userId != null) {
			sb.append(", userId=").append(userId);
		}
		sb.append('}');
		return sb.toString();
	}
}
