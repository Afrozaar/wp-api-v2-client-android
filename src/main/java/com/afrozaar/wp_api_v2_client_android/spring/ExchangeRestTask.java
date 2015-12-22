package com.afrozaar.wp_api_v2_client_android.spring;

import android.content.Context;
import android.os.AsyncTask;

import com.afrozaar.wp_api_v2_client_android.R;
import com.afrozaar.wp_api_v2_client_android.util.AuthUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by jay on 12/11/15.
 */
public class ExchangeRestTask<T> extends AsyncTask<Void, Integer, ResponseEntity<T>> {

    private ResponseEntity<T> exchange;
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private HttpMethod mHttpMethod;
    private HttpEntity mHttpEntity;
    private URI mUri;
    private Class<T> mResultClass;
    protected Context mContext;
    //private RestTaskCallback callback;
    private ResponseEntity<T> responseEntity;

/*
    public interface RestTaskCallback {
        <T> void onTaskSuccess(ResponseEntity<T> successResponse);
        <T> void onTaskFailed(ResponseEntity<T> failedResponse);
    }*/

    public ExchangeRestTask(Context context, URI uri, HttpMethod httpMethod, HttpEntity httpEntity, Class<T> resultClass) {
        this.mHttpMethod = httpMethod;
        this.mHttpEntity = httpEntity;
        this.mResultClass = resultClass;
        this.mUri = uri;
        this.mContext = context;
        //this.callback = callback;
    }

    @Override
    protected ResponseEntity<T> doInBackground(Void... params) {
        RestTemplate restTemplate = new RestTemplate();
        responseEntity = restTemplate.exchange(mUri, mHttpMethod, mHttpEntity, mResultClass);
        return responseEntity;
    }

    @Override
    protected void onPostExecute(ResponseEntity<T> t) {
        super.onPostExecute(t);
        if(t.getStatusCode().is2xxSuccessful()) {
            //callback.onTaskSuccess(t);
        }else{
            //callback.onTaskFailed(t);
        }
    }

    /*
    private <T, B> ResponseEntity<T> doExchange0(HttpMethod method, URI uri, Class<T> typeRef, B body) {
        final Two<String, String> authTuple = AuthUtil.getBasicAuth(username, password);
        final RequestEntity<B> entity = RequestEntity.method(method, uri).header(authTuple.k, authTuple.v).body(body);
        debugRequest(entity);
        final ResponseEntity<T> exchange = restTemplate.exchange(entity, typeRef);
        debugHeaders(exchange.getHeaders());
        return exchange;
    }*/
}
