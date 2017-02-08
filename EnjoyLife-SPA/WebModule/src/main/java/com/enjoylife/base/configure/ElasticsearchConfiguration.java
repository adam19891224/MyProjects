package com.enjoylife.base.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * ranmin-zhouyuhong
 * 2016/11/23
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.enjoylife.article.repository"})
public class ElasticsearchConfiguration {

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
    }

}
