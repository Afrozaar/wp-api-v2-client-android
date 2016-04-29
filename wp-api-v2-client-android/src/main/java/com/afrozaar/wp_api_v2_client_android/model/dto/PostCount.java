package com.afrozaar.wp_api_v2_client_android.model.dto;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/04/29.
 */
public class PostCount {

    /*
    * {"publish":"1","draft":0,"privatePub":0}
    */

    /**
     * Page count for published posts
     */
    public int publish;

    /**
     * Page count for draft posts
     */
    public int draft;

    /**
     * Page count for privately published posts
     */
    public int privatePub;

}
