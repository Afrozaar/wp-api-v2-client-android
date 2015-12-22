package com.afrozaar.wp_api_v2_client_android.spring;

import android.content.Context;
import android.os.AsyncTask;

import com.afrozaar.wp_api_v2_client_android.builder.PostBuilder;
import com.afrozaar.wp_api_v2_client_android.util.AuthUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/12/03.
 */
public abstract class BaseRestTask<R> extends AsyncTask<Void, Integer, R> {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private HttpMethod mHttpMethod;
    private URI mUri;
    private Class<R> mResultClass;
    protected Context mContext;
    //private RestTaskCallback callback;

    private String user = "admin";
    private String pass = "atI*pe$nqQ&N6pPcd&";

    protected PostBuilder mBuilder;

    public BaseRestTask(Context context, HttpMethod httpMethod, Class<R> resultClass) {
        this.mHttpMethod = httpMethod;
        this.mResultClass = resultClass;
        this.mContext = context;
        //this.callback = callback;
    }



    protected Context getContext(){
        return mContext;
    }

    protected void setURI(URI uri) {
        mUri = uri;
    }

    protected URI getUri() {
        return mUri;
    }

    @Override
    protected R doInBackground(Void... params) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        String temp = "";
        ResponseEntity<R> responseEntity = restTemplate.exchange(mUri, mHttpMethod, AuthUtil.getBasicAuth(user, pass, temp), mResultClass);

        return responseEntity.getBody();
    }

}
