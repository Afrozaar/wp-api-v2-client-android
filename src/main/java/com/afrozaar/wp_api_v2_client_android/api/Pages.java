package com.afrozaar.wp_api_v2_client_android.api;


import com.afrozaar.wp_api_v2_client_android.exception.PageNotFoundException;
import com.afrozaar.wp_api_v2_client_android.model.wp_v2.Page;
import com.afrozaar.wp_api_v2_client_android.model.wp_v2.PostStatus;

public interface Pages {

    Page createPage(Page page, PostStatus postStatus);

    Page getPage(Long pageId) throws PageNotFoundException;

    Page getPage(Long pageId, String context);

    //List<Page> getPages();

    Page updatePage(Page page);

    Page deletePage(Page page);

    Page deletePage(Page page, boolean force);

}
