package com.afrozaar.wp_api_v2_client_android.model.wp_v2;

/**
 * Created by jay on 12/9/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Links {

    @SerializedName("self")
    @Expose
    private List<Self> self = new ArrayList<Self>();
    @SerializedName("collection")
    @Expose
    private List<Collection> collection = new ArrayList<Collection>();
    @SerializedName("author")
    @Expose
    private List<Author> author = new ArrayList<Author>();
    @SerializedName("replies")
    @Expose
    private List<Reply> replies = new ArrayList<Reply>();
    @SerializedName("version-history")
    @Expose
    private List<VersionHistory> versionHistory = new ArrayList<VersionHistory>();
    @SerializedName("https://api.w.org/featuredmedia")
    @Expose
    private List<HttpsApiWOrgFeaturedmedium> httpsApiWOrgFeaturedmedia = new ArrayList<HttpsApiWOrgFeaturedmedium>();
    @SerializedName("https://api.w.org/attachment")
    @Expose
    private List<HttpsApiWOrgAttachment> httpsApiWOrgAttachment = new ArrayList<HttpsApiWOrgAttachment>();
    @SerializedName("https://api.w.org/term")
    @Expose
    private List<HttpsApiWOrgTerm> httpsApiWOrgTerm = new ArrayList<HttpsApiWOrgTerm>();
    @SerializedName("https://api.w.org/meta")
    @Expose
    private List<HttpsApiWOrgMetum> httpsApiWOrgMeta = new ArrayList<HttpsApiWOrgMetum>();

    /**
     * @return The self
     */
    public List<Self> getSelf() {
        return self;
    }

    /**
     * @param self The self
     */
    public void setSelf(List<Self> self) {
        this.self = self;
    }

    public Links withSelf(List<Self> self) {
        this.self = self;
        return this;
    }

    /**
     * @return The collection
     */
    public List<Collection> getCollection() {
        return collection;
    }

    /**
     * @param collection The collection
     */
    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

    public Links withCollection(List<Collection> collection) {
        this.collection = collection;
        return this;
    }

    /**
     * @return The author
     */
    public List<Author> getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public Links withAuthor(List<Author> author) {
        this.author = author;
        return this;
    }

    /**
     * @return The replies
     */
    public List<Reply> getReplies() {
        return replies;
    }

    /**
     * @param replies The replies
     */
    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public Links withReplies(List<Reply> replies) {
        this.replies = replies;
        return this;
    }

    /**
     * @return The versionHistory
     */
    public List<VersionHistory> getVersionHistory() {
        return versionHistory;
    }

    /**
     * @param versionHistory The version-history
     */
    public void setVersionHistory(List<VersionHistory> versionHistory) {
        this.versionHistory = versionHistory;
    }

    public Links withVersionHistory(List<VersionHistory> versionHistory) {
        this.versionHistory = versionHistory;
        return this;
    }

    /**
     * @return The httpsApiWOrgFeaturedmedia
     */
    public List<HttpsApiWOrgFeaturedmedium> getHttpsApiWOrgFeaturedmedia() {
        return httpsApiWOrgFeaturedmedia;
    }

    /**
     * @param httpsApiWOrgFeaturedmedia The https://api.w.org/featuredmedia
     */
    public void setHttpsApiWOrgFeaturedmedia(List<HttpsApiWOrgFeaturedmedium> httpsApiWOrgFeaturedmedia) {
        this.httpsApiWOrgFeaturedmedia = httpsApiWOrgFeaturedmedia;
    }

    public Links withHttpsApiWOrgFeaturedmedia(List<HttpsApiWOrgFeaturedmedium> httpsApiWOrgFeaturedmedia) {
        this.httpsApiWOrgFeaturedmedia = httpsApiWOrgFeaturedmedia;
        return this;
    }

    /**
     * @return The httpsApiWOrgAttachment
     */
    public List<HttpsApiWOrgAttachment> getHttpsApiWOrgAttachment() {
        return httpsApiWOrgAttachment;
    }

    /**
     * @param httpsApiWOrgAttachment The https://api.w.org/attachment
     */
    public void setHttpsApiWOrgAttachment(List<HttpsApiWOrgAttachment> httpsApiWOrgAttachment) {
        this.httpsApiWOrgAttachment = httpsApiWOrgAttachment;
    }

    public Links withHttpsApiWOrgAttachment(List<HttpsApiWOrgAttachment> httpsApiWOrgAttachment) {
        this.httpsApiWOrgAttachment = httpsApiWOrgAttachment;
        return this;
    }

    /**
     * @return The httpsApiWOrgTerm
     */
    public List<HttpsApiWOrgTerm> getHttpsApiWOrgTerm() {
        return httpsApiWOrgTerm;
    }

    /**
     * @param httpsApiWOrgTerm The https://api.w.org/term
     */
    public void setHttpsApiWOrgTerm(List<HttpsApiWOrgTerm> httpsApiWOrgTerm) {
        this.httpsApiWOrgTerm = httpsApiWOrgTerm;
    }

    public Links withHttpsApiWOrgTerm(List<HttpsApiWOrgTerm> httpsApiWOrgTerm) {
        this.httpsApiWOrgTerm = httpsApiWOrgTerm;
        return this;
    }

    /**
     * @return The httpsApiWOrgMeta
     */
    public List<HttpsApiWOrgMetum> getHttpsApiWOrgMeta() {
        return httpsApiWOrgMeta;
    }

    /**
     * @param httpsApiWOrgMeta The https://api.w.org/meta
     */
    public void setHttpsApiWOrgMeta(List<HttpsApiWOrgMetum> httpsApiWOrgMeta) {
        this.httpsApiWOrgMeta = httpsApiWOrgMeta;
    }

    public Links withHttpsApiWOrgMeta(List<HttpsApiWOrgMetum> httpsApiWOrgMeta) {
        this.httpsApiWOrgMeta = httpsApiWOrgMeta;
        return this;
    }

}
