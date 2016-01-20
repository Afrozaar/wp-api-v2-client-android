package com.afrozaar.wp_api_v2_client_android.util;

import android.content.Context;

import com.afrozaar.wp_api_v2_client_android.R;


/**
 * Created by jlo on 2015/05/18.
 */
public class WordpressPreferenceHelper extends BasePreferenceHelper {

    private static final String APP_PREFS = "wordpress_preferences";

    private static final String PREF_INITIAL_SETUP_DONE = "pref.initial_setup_done";

    /* USER CONSTANTS */
    private static final int PREF_USER_HAS_PROFILE = R.string.pref_id_has_profile;
    private static final int PREF_USER_ID = R.string.pref_id_username;
    private static final int PREF_USER_EMAIL = R.string.pref_id_email;
    private static final int PREF_USER_NAME = R.string.pref_id_name;
    private static final int PREF_USER_FIRST_NAME = R.string.pref_id_first_name;
    private static final int PREF_USER_LAST_NAME = R.string.pref_id_last_name;
    private static final int PREF_USER_PROFILE_PIC = R.string.pref_id_profile_pic;

    private static final int PREF_USER_WP_ID = R.string.pref_id_wordpress_id;
    private static final int PREF_USER_WP_USERNAME = R.string.pref_id_wordpress_username;
    private static final int PREF_USER_WP_PASSWORD = R.string.pref_id_wordpress_password;

    private static WordpressPreferenceHelper sInstance = null;

    public static WordpressPreferenceHelper with(Context context) {
        if (sInstance == null) {
            sInstance = new WordpressPreferenceHelper(context);
        }

        return sInstance;
    }

    private WordpressPreferenceHelper(Context context) {
        if (context == null) {
            throw new IllegalStateException("Context can not be null!");
        }
        mContext = context;
    }

    @Override
    protected String getAppPreferenceName() {
        return APP_PREFS;
    }

    /* APP PREFERENCES */

    public boolean isInitialSetupDone() {
        return getBooleanPref(PREF_INITIAL_SETUP_DONE);
    }

    public void setInitialSetupDone(boolean value) {
        putBooleanPref(PREF_INITIAL_SETUP_DONE, value);
    }

    public void resetUserState() {

        setInitialSetupDone(false);
    }

    // USER PREFERENCES

    public WordpressPreferenceHelper setUserHasProfile(boolean hasProfile) {
        String pref = mContext.getString(PREF_USER_HAS_PROFILE);
        putBooleanPref(pref, hasProfile);
        return this;
    }

    public boolean getUserHasProfile() {
        String pref = mContext.getString(PREF_USER_HAS_PROFILE);
        return getBooleanPref(pref);
    }

    public WordpressPreferenceHelper setUserId(String id) {
        String pref = mContext.getString(PREF_USER_ID);
        putStringPref(pref, id);
        return this;
    }

    public String getUserId() {
        String pref = mContext.getString(PREF_USER_ID);
        return getStringPref(pref);
    }

    public WordpressPreferenceHelper setUserEmail(String email) {
        String pref = mContext.getString(PREF_USER_EMAIL);
        putStringPref(pref, email);
        return this;
    }

    public String getUserEmail() {
        String pref = mContext.getString(PREF_USER_EMAIL);
        return getStringPref(pref);
    }

    public WordpressPreferenceHelper setUserName(String name) {
        String pref = mContext.getString(PREF_USER_NAME);
        putStringPref(pref, name);
        return this;
    }

    public String getUserName() {
        String pref = mContext.getString(PREF_USER_NAME);
        return getStringPref(pref);
    }

    public WordpressPreferenceHelper setUserFirstName(String firstName) {
        String pref = mContext.getString(PREF_USER_FIRST_NAME);
        putStringPref(pref, firstName);
        return this;
    }

    public String getUserFirstName() {
        String pref = mContext.getString(PREF_USER_FIRST_NAME);
        return getStringPref(pref);
    }

    public WordpressPreferenceHelper setUserLastName(String lastName) {
        String pref = mContext.getString(PREF_USER_LAST_NAME);
        putStringPref(pref, lastName);
        return this;
    }

    public String getUserLastName() {
        String pref = mContext.getString(PREF_USER_LAST_NAME);
        return getStringPref(pref);
    }

    public WordpressPreferenceHelper setUserProfilePic(String pic) {
        String pref = mContext.getString(PREF_USER_PROFILE_PIC);
        putStringPref(pref, pic);
        return this;
    }

    public String getUserProfilePic() {
        String pref = mContext.getString(PREF_USER_PROFILE_PIC);
        return getStringPref(pref);
    }

    public WordpressPreferenceHelper setWordPressUserId(long id) {
        String pref = mContext.getString(PREF_USER_WP_ID);
        putLongPref(pref, id);
        return this;
    }

    public long getWordPressUserId() {
        String pref = mContext.getString(PREF_USER_WP_ID);
        return getLongPref(pref);
    }

    public boolean hasWordPressUserId() {
        return getWordPressUserId() != -1;
    }

    public WordpressPreferenceHelper setWordPressUsername(String username) {
        String pref = mContext.getString(PREF_USER_WP_USERNAME);
        putStringPref(pref, username);
        return this;
    }

    public String getWordPressUsername() {
        String pref = mContext.getString(PREF_USER_WP_USERNAME);
        return getStringPref(pref);
    }

    public WordpressPreferenceHelper setWordPressUserPassword(String password) {
        String pref = mContext.getString(PREF_USER_WP_PASSWORD);
        putStringPref(pref, password);
        return this;
    }

    public String getWordPressUserPassword() {
        String pref = mContext.getString(PREF_USER_WP_PASSWORD);
        return getStringPref(pref);
    }
}
