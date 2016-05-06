package com.service.impl;

import com.foundation.solr.factory.SolrFactory;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/5/3
 */
class BaseServiceImpl {

    Logger logger = Logger.getLogger("serviceLog");

    static SolrClient client;

    static{
        client = SolrFactory.getClient();
    }
}
