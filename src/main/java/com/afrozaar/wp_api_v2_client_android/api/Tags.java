package com.afrozaar.wp_api_v2_client_android.api;

import com.afrozaar.wp_api_v2_client_android.exception.TermNotFoundException;
import com.afrozaar.wp_api_v2_client_android.exception.WpApiParsedException;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Post;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Term;

import java.util.List;

public interface Tags {

    Term createTag(Term tagTerm) throws WpApiParsedException;

    List<Term> getTags();

    Term getTag(Long id) throws TermNotFoundException;

    Term deleteTag(Term tagTerm) throws TermNotFoundException;

    Term updateTag(Term tag);

    Term createPostTag(Post post, Term tag) throws WpApiParsedException;

    List<Term> getPostTags(Post post);

    Term deletePostTag(Post post, Term tagTerm, boolean force) throws TermNotFoundException;

    Term getPostTag(Post post, Term tagTerm) throws TermNotFoundException;
}
