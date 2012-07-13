package simperium.test.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * @author Erik Beeson
 */
@SuppressWarnings("ThrowableResultOfMethodCallIgnored")
public class SkipUnsupportedOperationsListener implements IInvokedMethodListener {
	public void beforeInvocation(IInvokedMethod method, ITestResult result) {
	}

	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		if(method.isTestMethod() && result.getThrowable() instanceof UnsupportedOperationException) {
			result.setStatus(ITestResult.SKIP);
		}
	}
}
