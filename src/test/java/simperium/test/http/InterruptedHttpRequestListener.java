package simperium.test.http;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * @author Erik Beeson
 */
@SuppressWarnings("ThrowableResultOfMethodCallIgnored")
public class InterruptedHttpRequestListener implements IInvokedMethodListener {
	public void beforeInvocation(IInvokedMethod method, ITestResult result) {
	}

	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		if(method.isTestMethod() && result.getThrowable() instanceof LoggingHttpClient.InterruptedHttpRequest) {
			result.setStatus(ITestResult.SUCCESS);
			result.setThrowable(null);
		}
	}
}
