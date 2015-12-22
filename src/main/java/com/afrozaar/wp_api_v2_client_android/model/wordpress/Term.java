package com.afrozaar.wp_api_v2_client_android.model.wordpress;

/**
 * Created by jay on 12/10/15.
 */
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Term {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("count")
    @Expose
    private long count;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("taxonomy")
    @Expose
    private String taxonomy;
    @SerializedName("parent")
    @Expose
    private long parent;
    @SerializedName("_links")
    @Expose
    private Links Links;

    /**
     *
     * @return
     * The id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(long id) {
        this.id = id;
    }

    public Term withId(long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return
     * The count
     */
    public long getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(long count) {
        this.count = count;
    }

    public Term withCount(long count) {
        this.count = count;
        return this;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Term withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    public Term withLink(String link) {
        this.link = link;
        return this;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Term withName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @return
     * The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     *
     * @param slug
     * The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Term withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    /**
     *
     * @return
     * The taxonomy
     */
    public String getTaxonomy() {
        return taxonomy;
    }

    /**
     *
     * @param taxonomy
     * The taxonomy
     */
    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public Term withTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
        return this;
    }

    /**
     *
     * @return
     * The parent
     */
    public long getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     * The parent
     */
    public void setParent(long parent) {
        this.parent = parent;
    }

    public Term withParent(long parent) {
        this.parent = parent;
        return this;
    }

    /**
     *
     * @return
     * The Links
     */
    public Links getLinks() {
        return Links;
    }

    /**
     *
     * @param Links
     * The _links
     */
    public void setLinks(Links Links) {
        this.Links = Links;
    }

    public Term withLinks(Links Links) {
        this.Links = Links;
        return this;
    }

    public Map<String, Object> asMap() {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();
        Optional<String> descripOptional = Optional.fromNullable(description);
        if(descripOptional.isPresent()){
            builder.put("description",descripOptional.get());
        }
        Optional<String> nameOptional = Optional.fromNullable(name);
        if(nameOptional.isPresent()){
            builder.put("name",nameOptional.get());
        }
        Optional<String> slugOptional = Optional.fromNullable(slug);
        if(slugOptional.isPresent()){
            builder.put("slug", slugOptional.get());
        }
        Optional<Long> parentOptional = Optional.fromNullable(parent);
        if (parentOptional.isPresent()){
            builder.put("parent", parentOptional.get());
        }
        return builder.build();
    }

}
