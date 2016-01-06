package com.afrozaar.wp_api_v2_client_android.api;

import com.afrozaar.wp_api_v2_client_android.exception.TermNotFoundException;
import com.afrozaar.wp_api_v2_client_android.model.wordpress.Term;

import java.util.List;

public interface Terms {

    List<Term> getTerms(String taxonomy);

    Term getTerm(String taxonomy, Long id) throws TermNotFoundException;

    Term updateTerm(String taxonomy, Term term);

    Term deleteTerm(String taxonomy, Term term) throws TermNotFoundException;

    List<Term> deleteTerms(String taxonomy, Term... terms);

}
