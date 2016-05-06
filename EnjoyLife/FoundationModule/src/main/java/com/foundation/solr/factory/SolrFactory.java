package com.foundation.solr.factory;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/5/5
 */
public class SolrFactory {

    private static SolrClient client = null;

    private static final String URL = "http://localhost:8080/solr/enjoy-article";

    public static SolrClient getClient(){

        if(client == null){
            client = new HttpSolrClient(URL);
        }

        return client;
    }

}
