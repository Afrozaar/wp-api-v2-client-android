package com.afrozaar.wp_api_v2_client_android.spring;

import android.content.Context;

import com.afrozaar.wp_api_v2_client_android.model.wordpress.Post;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/12/04.
 */
public class UpdatePostTask extends BaseRestTask<String> {

    private String user = "admin";
    private String pass = "atI*pe$nqQ&N6pPcd&";

    private Post mPost;

    public UpdatePostTask(Context context, int postId, Post post) {
        super(context, HttpMethod.POST, String.class);

        mPost = post;
        //setURI(mBuilder.forPost(postId).getUri());
    }

    @Override
    protected String doInBackground(Void... params) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

        //String body = "{\"title\":\"bla bla bla bla\",\"content\":\"wtf is going on here\"}";
        Map<String, Object> body = new HashMap<>();
        body.put("title","blablblblblblblblbllb");
        body.put("content","THIS IS CHANGED CONTENT");

        HttpAuthentication authHeader = new HttpBasicAuthentication(user, pass);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAuthorization(authHeader);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Map<String,Object>> entity = new HttpEntity<>(body, httpHeaders);
        log.info("Http Entity Object : {}", entity);
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUri(), HttpMethod.PUT, entity, String.class);

        //ResponseEntity<String> responseEntity = restTemplate.postForEntity(getUri(), entity, String.class);
        System.out.println(responseEntity.toString());
        return responseEntity.getBody();
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        System.out.println("====== GOT AN UPDATE RESULT");
        System.out.println(s);
    }
}
