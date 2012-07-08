package simperium;

import java.util.Arrays;

/**
 * @author Handcrafted with love by <a href="https://github.com/ebeeson">Erik Beeson</a>
 * @see <a href="https://github.com/ebeeson/simperium-java">https://github.com/ebeeson/simperium-java</a>
 * @see <a href="https://simperium.com/">https://simperium.com/</a>
 * @see <a href="https://simperium.com/docs/reference/http/">https://simperium.com/docs/reference/http/</a>
 */
public class BucketIndex<T> {
	private String current;
	private VersionedObject<T>[] index;

	public String getCurrent() {
		return current;
	}

	public VersionedObject<T>[] getIndex() {
		return index;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("BucketIndex");
		sb.append("{current='").append(current).append('\'');
		sb.append(", index=").append(index == null ? "null" : Arrays.asList(index).toString());
		sb.append('}');
		return sb.toString();
	}
}
