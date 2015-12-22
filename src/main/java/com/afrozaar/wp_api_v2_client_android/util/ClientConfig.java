package com.afrozaar.wp_api_v2_client_android.util;

/**
 * Created by jay on 12/10/15.
 */
public class ClientConfig {

    Wordpress wordpress;
    boolean debug;

    public ClientConfig(){}

    private ClientConfig(boolean debug, Wordpress wordpress){
        this.debug = debug;
        this.wordpress = wordpress;
    }

    public Wordpress getWordpress() {
        return wordpress;
    }

    public void setWordpress(Wordpress wordpress) {
        this.wordpress = wordpress;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public static ClientConfig create(String baseUrl, String username, String password, boolean debug) {
        return new ClientConfig(debug, new Wordpress(baseUrl, username, password));
    }

    public static class Wordpress{
        String mUsername;
        String mPassword;
        String mBaseUrl;

        Wordpress(){}

        private Wordpress(String bUrl,String user, String pass){
            this.mUsername = user;
            this.mBaseUrl = bUrl;
            this.mPassword = pass;
        }

        public String getmUsername() {
            return mUsername;
        }

        public void setmUsername(String mUsername) {
            this.mUsername = mUsername;
        }

        public String getmPassword() {
            return mPassword;
        }

        public void setmPassword(String mPassword) {
            this.mPassword = mPassword;
        }

        public String getmBaseUrl() {
            return mBaseUrl;
        }

        public void setmBaseUrl(String mBaseUrl) {
            this.mBaseUrl = mBaseUrl;
        }


    }
}
