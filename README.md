simperium-java
==============

SimperiumConfiguration
	Interface for configuring the HTTP communication and object serialization mechanisms.
	Does *not* configure app id or any keys.
DefaultSimperiumConfiguration
	Default implementation uses a DefaultHttpClient with a default PoolingClientConnectionManager
	and Gson configured with TypeAdapters for com.google.gson.Json* types.

SimperiumApplication
	The main entry point. Instantiate with an app id, and optionally a SimperiumConfiguration.


AuthAccess
	User authorization operations available with the application's api key.
UserAccess
	User specific operations available with a user's access token.
AdminAccess
	Administrative operations available with the application's admin key.

SimperiumUser
	User info response from creating or authorizing a user.
SimperiumBucket
	Operations related to a particular bucket for a particular user.

VersionedObject
	Represents an object by its ID, version, and optionally data.
BucketIndex
	Response from a bucket index; that is, a cv and a list of VersionedObjects
BucketChanges
	Used for long polling for real-time bucket changes. Returns one change at a time, blocking if none are available.

UserExistsException
	Thrown if trying to create a use that already exists in this application.

SimperiumBase
	Internal only base class.
