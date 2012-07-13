package simperium.test.logback;

import ch.qos.logback.core.OutputStreamAppender;
import org.testng.Reporter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A logback appender that writes to the TestNG Reporter API.
 */
public class TestNGReporterAppender<E> extends OutputStreamAppender<E> {
	public void start() {
		setOutputStream(new ByteArrayOutputStream() {
			public synchronized void flush() throws IOException {
				Reporter.log(this.toString());
				System.out.println(this.toString());
				this.reset();
			}
		});
		super.start();
	}
}
