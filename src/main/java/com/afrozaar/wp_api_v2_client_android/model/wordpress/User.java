package com.afrozaar.wp_api_v2_client_android.model.wordpress;

import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jay on 12/18/15.
 */
public class User {

    @SerializedName("avatar_urls")
    @Expose
    private AvatarUrls avatarUrls;
    @SerializedName("capabilities")
    @Expose
    private Capabilities capabilities;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("registered_date")
    @Expose
    private String registeredDate;
    @SerializedName("roles")
    @Expose
    private List<String> roles = new ArrayList<String>();
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("_links")
    @Expose
    private Links Links;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;

    /**
     * @return The avatarUrls
     */
    public AvatarUrls getAvatarUrls() {
        return avatarUrls;
    }

    /**
     * @param avatarUrls The avatar_urls
     */
    public void setAvatarUrls(AvatarUrls avatarUrls) {
        this.avatarUrls = avatarUrls;
    }

    public User withAvatarUrls(AvatarUrls avatarUrls) {
        this.avatarUrls = avatarUrls;
        return this;
    }

    /**
     * @return The capabilities
     */
    public Capabilities getCapabilities() {
        return capabilities;
    }

    /**
     * @param capabilities The capabilities
     */
    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public User withCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
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

    public User withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * @return The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * @return The id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(long id) {
        this.id = id;
    }

    public User withId(long id) {
        this.id = id;
        return this;
    }

    /**
     * @return The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * @return The link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    public User withLink(String link) {
        this.link = link;
        return this;
    }

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

    public User withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return The nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname The nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    /**
     * @return The registeredDate
     */
    public String getRegisteredDate() {
        return registeredDate;
    }

    /**
     * @param registeredDate The registered_date
     */
    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public User withRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
        return this;
    }

    /**
     * @return The roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * @param roles The roles
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public User withRoles(List<String> roles) {
        this.roles = roles;
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

    public User withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public User withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * @return The Links
     */
    public Links getLinks() {
        return Links;
    }

    /**
     * @param Links The _links
     */
    public void setLinks(Links Links) {
        this.Links = Links;
    }

    public User withLinks(Links Links) {
        this.Links = Links;
        return this;
    }

    /**
     * @return The Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public User withUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * @return The Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public static Map<String, Object> fieldsFrom(User user) {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();

        //populateEntry(user.getCapabilities(), builder, "capabilities");
        populateEntry(user.getDescription(), builder, "description");
        /*if (post.getContent() != null) {
            populateEntry(post.getContent().getRendered(), builder, "content");
        }*/
        populateEntry(user.getEmail(), builder, "email");
        populateEntry(user.getFirstName(), builder, "first_name");
        populateEntry(user.getLastName(), builder, "last_name");
        populateEntry(user.getName(), builder, "name");
        populateEntry(user.getNickname(), builder, "nickname");
        //populateEntry(user.getRoles(), builder, "nickname");
        populateEntry(user.getSlug(), builder, "slug");
        populateEntry(user.getUsername(), builder, "username");
        populateEntry(user.getPassword(), builder, "password");
        return builder.build();
    }

    private static void populateEntry(Object value, ImmutableMap.Builder<String, Object> builder, String key) {
        if (value != null) { //Optional.fromNullable(value).isPresent()
            builder.put(key, value);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "avatarUrls=" + avatarUrls +
                ", capabilities=" + capabilities +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id=" + id +
                ", lastName='" + lastName + '\'' +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                ", roles=" + roles +
                ", slug='" + slug + '\'' +
                ", url='" + url + '\'' +
                ", Links=" + Links +
                '}';
    }
}
