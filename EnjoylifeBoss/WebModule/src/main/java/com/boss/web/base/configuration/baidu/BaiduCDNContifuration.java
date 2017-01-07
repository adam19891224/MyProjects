package com.boss.web.base.configuration.baidu;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by Adam
 * 2017/1/7
 */
@SpringBootConfiguration
public class BaiduCDNContifuration {

    @Value("${baidu.sdk.keyid}")
    private String keyId;
    @Value("${baidu.sdk.key}")
    private String key;
    @Value("${image.host}")
    private String host;

    @Bean
    public BosClient getClient(){
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(keyId, key));
        config.setEndpoint(host);
        return new BosClient(config);
    }
}
