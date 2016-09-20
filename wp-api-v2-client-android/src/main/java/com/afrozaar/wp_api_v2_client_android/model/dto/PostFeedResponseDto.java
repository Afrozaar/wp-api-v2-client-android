package com.afrozaar.wp_api_v2_client_android.model.dto;

import java.util.List;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/09/06.
 */
public class PostFeedResponseDto {

    public int currentPage;
    public int maxPages;

    public List<PostStreamItem> streamItems;
}
