package simperium;

import com.google.gson.annotations.SerializedName;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class VersionedObject<T> {
	@SerializedName("v")
	private long version;
	private String id;
	private T data;

	public VersionedObject() {
	}

	public VersionedObject(String version, String id, T data) {
		this(Long.parseLong(version, 10), id, data);
	}

	public VersionedObject(long version, String id, T data) {
		this.version = version;
		this.id = id;
		this.data = data;
	}

	public long getVersion() {
		return version;
	}

	public String getId() {
		return id;
	}

	public T getData() {
		return data;
	}

	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof VersionedObject)) return false;

		VersionedObject that = (VersionedObject) o;

		return version == that.version
				&& !(data != null ? !data.equals(that.data) : that.data != null) &&
				!(id != null ? !id.equals(that.id) : that.id != null);

	}

	public int hashCode() {
		int result = (int) (version ^ (version >>> 32));
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (data != null ? data.hashCode() : 0);
		return result;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("VersionedObject");
		sb.append("[").append(id).append('[').append(version).append(']');
		sb.append(":").append(data);
		sb.append("]");
		return sb.toString();
	}
}
