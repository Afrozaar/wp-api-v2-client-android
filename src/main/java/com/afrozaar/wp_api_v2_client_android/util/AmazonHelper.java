package com.afrozaar.wp_api_v2_client_android.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Region;

import java.io.File;

/**
 * Created by jay on 1/14/16.
 */
public class AmazonHelper {

    /* -- Key for Cognito Identity Pool in Android Manifest -- */
    private final static String COGNITO_IDENTITY_POOL = "com.afrozaar.wp_api_v2_client_android.CognitoIdentityPoolId";
    /* -- Key for Identity Pool Region in Android Manifest -- */
    private final static String IDENTITY_POOL_REGION = "com.afrozaar.wp_api_v2_client_android.IdentityPoolRegion";
    /* -- Key for S3 Bucket in Android Manifest -- */
    private final static String S3_BUCKET_NAME = "com.afrozaar.wp_api_v2_client_android.S3BucketName";


    private String mCognitoIdentityPoolId = null;
    private String mIdentityPoolRegion = null;
    private String mS3BucketName = null;
    private static AmazonHelper sInstance = null;
    private Context mContext;
    private CognitoCachingCredentialsProvider mCredentialsProvider;

    public static AmazonHelper with(Context context) {
        if (sInstance == null) {
            sInstance = new AmazonHelper(context);
        }
        return sInstance;
    }

    public String uploadFileAndReturnUrl(Uri fileUri){
        if(sInstance == null){
            throw new IllegalStateException("Please use the AmazonHelper.with(context) declaration to define the needed context");
        }
        AmazonS3 s3 = new AmazonS3Client(mCredentialsProvider);
        File file;
        String url = "";
        TransferUtility transferUtility = new TransferUtility(s3,mContext);
        TransferObserver transferObserver = transferUtility.upload(
                mS3BucketName,
                file.getName(),
                file);

        return url;
    }

    private AmazonHelper(Context context) {
        if (context == null) {
            throw new IllegalStateException("Context can not be null!");
        }
        mContext = context;
        loadMetaDataFromManifest(context);

        mCredentialsProvider = new CognitoCachingCredentialsProvider(
          mContext.getApplicationContext(),
          mCognitoIdentityPoolId,
          Regions.fromName(mIdentityPoolRegion)
        );
    }

    public String getIdentityPoolRegion() {
        return mIdentityPoolRegion;
    }

    public String getCognitoIdentityPoolId() {
        return mCognitoIdentityPoolId;
    }

    private void loadMetaDataFromManifest(Context context){
        ApplicationInfo ai = null;
        try {
            ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return;
        }

        if (ai == null || ai.metaData == null) {
            throw new Resources.NotFoundException("Make sure Amazon Cognito Pool id, Region and S3 bucket name are present in Android Manifest");
        }

        if (mCognitoIdentityPoolId == null) {
            Object cognitoPoolId = ai.metaData.get(COGNITO_IDENTITY_POOL);
            if (cognitoPoolId instanceof String) {
                mCognitoIdentityPoolId = (String) cognitoPoolId;
            } else if (cognitoPoolId instanceof Integer) {
                mCognitoIdentityPoolId = cognitoPoolId.toString();
            }
        }

        if(mIdentityPoolRegion == null){
            Object identityPoolRegion = ai.metaData.get(IDENTITY_POOL_REGION);
            if(identityPoolRegion instanceof String){
                mIdentityPoolRegion = (String) identityPoolRegion;
            }else{
                throw new Resources.NotFoundException("Identity Pool Region value is incorrect. Please ensure you have the correct region name.");
            }
        }

        if(mS3BucketName == null){
            Object s3BucketName = ai.metaData.get(S3_BUCKET_NAME);
            if (s3BucketName instanceof String){
                mS3BucketName = (String) s3BucketName;
            }else{
                throw new Resources.NotFoundException("S3 Bucket Name must be defined in you Android Manifest.");
            }
        }
    }

}
