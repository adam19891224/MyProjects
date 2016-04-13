package com.foundation.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/20
 */
public class FileUploadUtils {

    public static final String WEB_URL = "http://image.adamzhou.cn/";

//    public static final String WEB_URL = "http://localhost:9999/";

    public static CloseableHttpResponse uploadFile(String url, HttpEntity entity){

        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(entity);
        //创建httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            return httpclient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPostfix(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    public static String getResponseString(CloseableHttpResponse response){

        String res = "null";

        if(response != null){
            System.out.println(response.getStatusLine());
            HttpEntity responseEntity = response.getEntity();
            InputStream instream = null;
            BufferedReader reader = null;
            try {
                instream = responseEntity.getContent();
                reader = new BufferedReader(new InputStreamReader(instream, "utf-8"));

                res = reader.readLine();
                //消耗掉response
                EntityUtils.consume(responseEntity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                    if (instream != null) {
                        instream.close();
                    }
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  res;
    }
}
