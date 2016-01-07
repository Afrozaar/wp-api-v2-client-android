package com.afrozaar.wp_api_v2_client_android.api;

import com.afrozaar.wp_api_v2_client_android.exception.TermNotFoundException;
import com.afrozaar.wp_api_v2_client_android.model.wp_v2.Term;

import java.util.List;

public interface Categories {

    List<Term> getCategories();

    Term getCategory(Long id);

    Term createCategory(Term categoryTerm);

    Term deleteCategory(Term categoryTerm) throws TermNotFoundException;

    List<Term> deleteCategories(Term... terms);
}
