package simperium;

import com.google.gson.Gson;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class SimperiumBucket<T> {
	protected SimperiumBucket(Gson gson, String appId, String name, Class<T> clazz, String accessToken) {
		throw new UnsupportedOperationException("simperium.SimperiumBucket#SimperiumBucket[gson, appId, name, clazz, accessToken]");
	}

	protected SimperiumBucket(Gson gson, String appId, String name, Class<T> clazz, String adminKey, String userId) {
		throw new UnsupportedOperationException("simperium.SimperiumBucket#SimperiumBucket[gson, appId, name, clazz, adminKey, userId]");
	}

	public BucketIndex<T> list() {
		throw new UnsupportedOperationException("simperium.SimperiumBucket#list[]");
	}

	public VersionedObject<T> getObject(String id) {
		throw new UnsupportedOperationException("simperium.SimperiumBucket#getObject[id]");
	}

	public VersionedObject<T> getObject(String id, int version) {
		throw new UnsupportedOperationException("simperium.SimperiumBucket#getObject[id, version]");
	}

	public String updateObject(String id, T object) {
		throw new UnsupportedOperationException("simperium.SimperiumBucket#updateObject[id, object]");
	}

	public String updateObject(String id, T object, int version) {
		throw new UnsupportedOperationException("simperium.SimperiumBucket#updateObject[id, object, version]");
	}

	public String deleteObject(String id) {
		throw new UnsupportedOperationException("simperium.SimperiumBucket#deleteObject[id]");
	}

	public String deleteObject(String id, int version) {
		throw new UnsupportedOperationException("simperium.SimperiumBucket#deleteObject[id, version]");
	}
}
