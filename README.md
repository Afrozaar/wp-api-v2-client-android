# README # 

WP-API v2 Android 

An Android library for the [WP REST API v2](http://wp-api.org/)

(Currently supports **WP REST API Version 2.0-beta12**)

This library contains the necessary REST calls to interact with the WP REST API and also a database for storing the WP objects.
It also contains some custom REST calls that expand WP REST API which can be found in the following plugin:
[WP REST API Afrozaar Extras](https://github.com/Afrozaar/wp-api-v2-afrozaar-extras)

### Dependencies ###

## Wordpress ##

* WP REST API Version 2.0-beta13 (https://wordpress.org/plugins/rest-api/)
* WP REST API - Meta Endpoints Version 0.1.0 (https://wordpress.org/plugins/rest-api-meta-endpoints/)
* WP REST API - Afrozaar Extras Version 0.8 (https://github.com/Afrozaar/wp-api-v2-afrozaar-extras)

## Android ##

* Retrofit 2.0.0-beta4 (http://square.github.io/retrofit/)
* OkHttp 3.1.1 (http://square.github.io/okhttp/)
* Gson 2.4 (https://github.com/google/gson)

### Usage - REST interface ###

Initiate a new WpClientRetrofit object, for example:

    String baseUrl = "http://example.com"
    String username = "myuser"
    String password = "mypass"
    boolean debug = true;
    
    WpClientRetrofit client = new WpClientRetrofit(baseUrl, username, password, debug);
    
The individual REST calls can then be made using the client. 

There are 2 ways of calling the REST interfaces; using the Callback mechanism or directly.

- Using the Callbacks allow you specify a callback object that will be called when the REST call has finished
~~~~
public void createPost(Post post, WordPressRestResponse<Post> callback);
~~~~
- The direct method returns the Call object and you'll need to manually execute it and parse the response.
~~~~
public Call<Post> createPost(Post post);
~~~~

The callback method can be used on the MainThread, while the other one will need to be executed on a background thread.

**WIP: Not all calls has both methods implemented yet**
 
### Database & Models ###

The library includes a database and related model classes for storing the WP objects.
The majority of the base WP object models are supported plus support for having multiple sites.

* WordPressContract : Contains database schema for all tables, including helper methods for inserting/updating content.
* WordPressDatabase : SqlLiteOpenHelper subclass for working with database file.

There are also POC/WIP tasks for working with the database and make it easier to run queries without blocking the Main thread and impacting performance.
The **com.afozaar.wp_api_v2_client_android.data.tasks** package contains a base AsyncTask for doing database operations including subtasks for CRUD operations. 
These tasks uses callbacks to notify the app when their operations have finished.

