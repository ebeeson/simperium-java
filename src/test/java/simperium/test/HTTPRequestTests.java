package simperium.test;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import simperium.*;
import simperium.test.http.LoggingHttpClient;

import java.io.IOException;

/**
 * @author Erik Beeson
 */
@Test
public class HTTPRequestTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(HTTPRequestTests.class);

	private static final String APP_ID = "test-app";

	private static final String KEY_AUTH = "test-auth-key";
	private static final String KEY_USER = "test-user-token";
	private static final String KEY_ADMIN = "test-admin-key";

	private static final String USERNAME = "testUsername";
	private static final String PASSWORD = "testPassword";

	private static final String BUCKET = "test-bucket";

	private static final String USER_ID = "test-user-id";

	private static final String OBJECT_ID = "test-object-id";

	private static final int OBJECT_VERSION = 804;


	private SimperiumApplication app = new SimperiumApplication(APP_ID, new DefaultSimperiumConfiguration(new LoggingHttpClient()));

	private AuthAccess auth = app.getAuthAccess(KEY_AUTH);
	private UserAccess user = app.getUserAccess(KEY_USER);
	private AdminAccess admin = app.getAdminAccess(KEY_ADMIN);

	private SimperiumBucket<JsonObject> userBucket = user.getBucket(BUCKET);
	private SimperiumBucket<JsonObject> adminBucket = admin.getBucket(BUCKET, USER_ID);


	@BeforeSuite
	public void setUp() throws Exception {
		LOGGER.info("APP_ID = {}", APP_ID);

		LOGGER.info("KEY_AUTH = {}", KEY_AUTH);
		LOGGER.info("KEY_USER = {}", KEY_USER);
		LOGGER.info("KEY_ADMIN = {}", KEY_ADMIN);

		LOGGER.info("USERNAME = {}", USERNAME);
		LOGGER.info("PASSWORD = {}", PASSWORD);

		LOGGER.info("BUCKET = {}", BUCKET);
		LOGGER.info("USER_ID = {}", USER_ID);
	}

	public void authAuthorize() throws IOException {
		auth.authorize(USERNAME, PASSWORD);
	}

	public void authCreate() throws IOException, UserExistsException {
		auth.create(USERNAME, PASSWORD);
	}

	public void authUpdate() throws IOException {
		auth.update(USERNAME, PASSWORD, USERNAME, PASSWORD);
	}

	public void adminDelete() throws IOException {
		admin.delete(USERNAME);
	}

	public void adminResetPassword() throws IOException {
		admin.resetPassword(USERNAME, PASSWORD);
	}

	public void userBucketList() throws IOException {
		userBucket.list();
	}

	public void userBucketGetObject() throws IOException {
		userBucket.getObject(OBJECT_ID);
	}

	public void userBucketGetObjectVersion() throws IOException {
		userBucket.getObject(OBJECT_ID, OBJECT_VERSION);
	}

	public void userBucketDelete() throws IOException {
		userBucket.deleteObject(OBJECT_ID);
	}

	public void userBucketDeleteVersion() throws IOException {
		userBucket.deleteObject(OBJECT_ID, OBJECT_VERSION);
	}

	public void adminBucketList() throws IOException {
		adminBucket.list();
	}

	public void adminBucketGetObject() throws IOException {
		adminBucket.getObject(OBJECT_ID);
	}

	public void adminBucketGetObjectVersion() throws IOException {
		adminBucket.getObject(OBJECT_ID, OBJECT_VERSION);
	}

	public void adminBucketDelete() throws IOException {
		adminBucket.deleteObject(OBJECT_ID);
	}

	public void adminBucketDeleteVersion() throws IOException {
		adminBucket.deleteObject(OBJECT_ID, OBJECT_VERSION);
	}

	public void userBucketChanges() throws IOException {
		user.getBucketChanges(BUCKET, null).nextChange();
	}

	public void adminBucketChanges() throws IOException {
		admin.getBucketChanges(BUCKET, true, true, true, null).nextChange();
	}
}
