package com.web.configure;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * Adam
 * 2016/8/4
 */
@Configuration
public class SolrClientConfiguration {

    private SolrClient solrClient;

    @Value("${spring.data.solr.host}")
    private String solrUrl;

    @PreDestroy
    public void close() {
        if (this.solrClient != null) {
            try {
                this.solrClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Bean
    public SolrClient SolrServer(){
        if(solrClient == null)
            solrClient = new HttpSolrClient(solrUrl);

        return this.solrClient;
    }
}
