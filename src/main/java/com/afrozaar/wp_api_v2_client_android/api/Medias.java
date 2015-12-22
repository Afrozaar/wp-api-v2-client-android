package com.afrozaar.wp_api_v2_client_android.api;

import com.afrozaar.wp_api_v2_client_android.exception.WpApiParsedException;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Media;

import org.springframework.core.io.Resource;

import java.util.List;

/**
 * Created by jay on 12/10/15.
 */
public interface Medias {

    Media createMediaItem(Media media, Resource resource) throws WpApiParsedException;

    List<Media> getMedia();

    Media getMedia(Integer id);

//    Media updateMediaItem();
//
//    boolean deleteMediaItem();
}
