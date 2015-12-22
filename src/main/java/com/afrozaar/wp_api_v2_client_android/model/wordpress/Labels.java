package com.afrozaar.wp_api_v2_client_android.model.wordpress;

/**
 * Created by jay on 12/10/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Labels {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("singular_name")
    @Expose
    private String singularName;
    @SerializedName("search_items")
    @Expose
    private String searchItems;
    @SerializedName("popular_items")
    @Expose
    private Object popularItems;
    @SerializedName("all_items")
    @Expose
    private String allItems;
    @SerializedName("parent_item")
    @Expose
    private String parentItem;
    @SerializedName("parent_item_colon")
    @Expose
    private String parentItemColon;
    @SerializedName("edit_item")
    @Expose
    private String editItem;
    @SerializedName("view_item")
    @Expose
    private String viewItem;
    @SerializedName("update_item")
    @Expose
    private String updateItem;
    @SerializedName("add_new_item")
    @Expose
    private String addNewItem;
    @SerializedName("new_item_name")
    @Expose
    private String newItemName;
    @SerializedName("separate_items_with_commas")
    @Expose
    private Object separateItemsWithCommas;
    @SerializedName("add_or_remove_items")
    @Expose
    private Object addOrRemoveItems;
    @SerializedName("choose_from_most_used")
    @Expose
    private Object chooseFromMostUsed;
    @SerializedName("not_found")
    @Expose
    private String notFound;
    @SerializedName("no_terms")
    @Expose
    private String noTerms;
    @SerializedName("items_list_navigation")
    @Expose
    private String itemsListNavigation;
    @SerializedName("items_list")
    @Expose
    private String itemsList;
    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("name_admin_bar")
    @Expose
    private String nameAdminBar;

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

    public Labels withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return The singularName
     */
    public String getSingularName() {
        return singularName;
    }

    /**
     * @param singularName The singular_name
     */
    public void setSingularName(String singularName) {
        this.singularName = singularName;
    }

    public Labels withSingularName(String singularName) {
        this.singularName = singularName;
        return this;
    }

    /**
     * @return The searchItems
     */
    public String getSearchItems() {
        return searchItems;
    }

    /**
     * @param searchItems The search_items
     */
    public void setSearchItems(String searchItems) {
        this.searchItems = searchItems;
    }

    public Labels withSearchItems(String searchItems) {
        this.searchItems = searchItems;
        return this;
    }

    /**
     * @return The popularItems
     */
    public Object getPopularItems() {
        return popularItems;
    }

    /**
     * @param popularItems The popular_items
     */
    public void setPopularItems(Object popularItems) {
        this.popularItems = popularItems;
    }

    public Labels withPopularItems(Object popularItems) {
        this.popularItems = popularItems;
        return this;
    }

    /**
     * @return The allItems
     */
    public String getAllItems() {
        return allItems;
    }

    /**
     * @param allItems The all_items
     */
    public void setAllItems(String allItems) {
        this.allItems = allItems;
    }

    public Labels withAllItems(String allItems) {
        this.allItems = allItems;
        return this;
    }

    /**
     * @return The parentItem
     */
    public String getParentItem() {
        return parentItem;
    }

    /**
     * @param parentItem The parent_item
     */
    public void setParentItem(String parentItem) {
        this.parentItem = parentItem;
    }

    public Labels withParentItem(String parentItem) {
        this.parentItem = parentItem;
        return this;
    }

    /**
     * @return The parentItemColon
     */
    public String getParentItemColon() {
        return parentItemColon;
    }

    /**
     * @param parentItemColon The parent_item_colon
     */
    public void setParentItemColon(String parentItemColon) {
        this.parentItemColon = parentItemColon;
    }

    public Labels withParentItemColon(String parentItemColon) {
        this.parentItemColon = parentItemColon;
        return this;
    }

    /**
     * @return The editItem
     */
    public String getEditItem() {
        return editItem;
    }

    /**
     * @param editItem The edit_item
     */
    public void setEditItem(String editItem) {
        this.editItem = editItem;
    }

    public Labels withEditItem(String editItem) {
        this.editItem = editItem;
        return this;
    }

    /**
     * @return The viewItem
     */
    public String getViewItem() {
        return viewItem;
    }

    /**
     * @param viewItem The view_item
     */
    public void setViewItem(String viewItem) {
        this.viewItem = viewItem;
    }

    public Labels withViewItem(String viewItem) {
        this.viewItem = viewItem;
        return this;
    }

    /**
     * @return The updateItem
     */
    public String getUpdateItem() {
        return updateItem;
    }

    /**
     * @param updateItem The update_item
     */
    public void setUpdateItem(String updateItem) {
        this.updateItem = updateItem;
    }

    public Labels withUpdateItem(String updateItem) {
        this.updateItem = updateItem;
        return this;
    }

    /**
     * @return The addNewItem
     */
    public String getAddNewItem() {
        return addNewItem;
    }

    /**
     * @param addNewItem The add_new_item
     */
    public void setAddNewItem(String addNewItem) {
        this.addNewItem = addNewItem;
    }

    public Labels withAddNewItem(String addNewItem) {
        this.addNewItem = addNewItem;
        return this;
    }

    /**
     * @return The newItemName
     */
    public String getNewItemName() {
        return newItemName;
    }

    /**
     * @param newItemName The new_item_name
     */
    public void setNewItemName(String newItemName) {
        this.newItemName = newItemName;
    }

    public Labels withNewItemName(String newItemName) {
        this.newItemName = newItemName;
        return this;
    }

    /**
     * @return The separateItemsWithCommas
     */
    public Object getSeparateItemsWithCommas() {
        return separateItemsWithCommas;
    }

    /**
     * @param separateItemsWithCommas The separate_items_with_commas
     */
    public void setSeparateItemsWithCommas(Object separateItemsWithCommas) {
        this.separateItemsWithCommas = separateItemsWithCommas;
    }

    public Labels withSeparateItemsWithCommas(Object separateItemsWithCommas) {
        this.separateItemsWithCommas = separateItemsWithCommas;
        return this;
    }

    /**
     * @return The addOrRemoveItems
     */
    public Object getAddOrRemoveItems() {
        return addOrRemoveItems;
    }

    /**
     * @param addOrRemoveItems The add_or_remove_items
     */
    public void setAddOrRemoveItems(Object addOrRemoveItems) {
        this.addOrRemoveItems = addOrRemoveItems;
    }

    public Labels withAddOrRemoveItems(Object addOrRemoveItems) {
        this.addOrRemoveItems = addOrRemoveItems;
        return this;
    }

    /**
     * @return The chooseFromMostUsed
     */
    public Object getChooseFromMostUsed() {
        return chooseFromMostUsed;
    }

    /**
     * @param chooseFromMostUsed The choose_from_most_used
     */
    public void setChooseFromMostUsed(Object chooseFromMostUsed) {
        this.chooseFromMostUsed = chooseFromMostUsed;
    }

    public Labels withChooseFromMostUsed(Object chooseFromMostUsed) {
        this.chooseFromMostUsed = chooseFromMostUsed;
        return this;
    }

    /**
     * @return The notFound
     */
    public String getNotFound() {
        return notFound;
    }

    /**
     * @param notFound The not_found
     */
    public void setNotFound(String notFound) {
        this.notFound = notFound;
    }

    public Labels withNotFound(String notFound) {
        this.notFound = notFound;
        return this;
    }

    /**
     * @return The noTerms
     */
    public String getNoTerms() {
        return noTerms;
    }

    /**
     * @param noTerms The no_terms
     */
    public void setNoTerms(String noTerms) {
        this.noTerms = noTerms;
    }

    public Labels withNoTerms(String noTerms) {
        this.noTerms = noTerms;
        return this;
    }

    /**
     * @return The itemsListNavigation
     */
    public String getItemsListNavigation() {
        return itemsListNavigation;
    }

    /**
     * @param itemsListNavigation The items_list_navigation
     */
    public void setItemsListNavigation(String itemsListNavigation) {
        this.itemsListNavigation = itemsListNavigation;
    }

    public Labels withItemsListNavigation(String itemsListNavigation) {
        this.itemsListNavigation = itemsListNavigation;
        return this;
    }

    /**
     * @return The itemsList
     */
    public String getItemsList() {
        return itemsList;
    }

    /**
     * @param itemsList The items_list
     */
    public void setItemsList(String itemsList) {
        this.itemsList = itemsList;
    }

    public Labels withItemsList(String itemsList) {
        this.itemsList = itemsList;
        return this;
    }

    /**
     * @return The menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName The menu_name
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Labels withMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    /**
     * @return The nameAdminBar
     */
    public String getNameAdminBar() {
        return nameAdminBar;
    }

    /**
     * @param nameAdminBar The name_admin_bar
     */
    public void setNameAdminBar(String nameAdminBar) {
        this.nameAdminBar = nameAdminBar;
    }

    public Labels withNameAdminBar(String nameAdminBar) {
        this.nameAdminBar = nameAdminBar;
        return this;
    }

}
