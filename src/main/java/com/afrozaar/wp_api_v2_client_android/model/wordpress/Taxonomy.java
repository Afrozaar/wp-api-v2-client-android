package com.afrozaar.wp_api_v2_client_android.model.wordpress;

/**
 * Created by jay on 12/10/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Taxonomy {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("labels")
    @Expose
    private Labels labels;
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<String>();
    @SerializedName("show_cloud")
    @Expose
    private boolean showCloud;
    @SerializedName("hierarchical")
    @Expose
    private boolean hierarchical;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Taxonomy withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * @param slug The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Taxonomy withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Taxonomy withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * @return The labels
     */
    public Labels getLabels() {
        return labels;
    }

    /**
     * @param labels The labels
     */
    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public Taxonomy withLabels(Labels labels) {
        this.labels = labels;
        return this;
    }

    /**
     * @return The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * @param types The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Taxonomy withTypes(List<String> types) {
        this.types = types;
        return this;
    }

    /**
     * @return The showCloud
     */
    public boolean isShowCloud() {
        return showCloud;
    }

    /**
     * @param showCloud The show_cloud
     */
    public void setShowCloud(boolean showCloud) {
        this.showCloud = showCloud;
    }

    public Taxonomy withShowCloud(boolean showCloud) {
        this.showCloud = showCloud;
        return this;
    }

    /**
     * @return The hierarchical
     */
    public boolean isHierarchical() {
        return hierarchical;
    }

    /**
     * @param hierarchical The hierarchical
     */
    public void setHierarchical(boolean hierarchical) {
        this.hierarchical = hierarchical;
    }

    public Taxonomy withHierarchical(boolean hierarchical) {
        this.hierarchical = hierarchical;
        return this;
    }

}
