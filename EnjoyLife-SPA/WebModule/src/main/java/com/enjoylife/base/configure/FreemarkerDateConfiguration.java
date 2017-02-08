package com.enjoylife.base.configure;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/14
 */
@Component
class FreemarkerDateConfiguration implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        // 当前时间毫秒数 + 四位随机
        String strTimestamp = "";
        try {
            strTimestamp = String.valueOf(System.currentTimeMillis()) + getRandom(1000, 9999);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        env.getOut().write(strTimestamp);
    }

    private int getRandom(int min, int max) {
        Random random = new Random();
        return Integer.parseInt(String.valueOf(random.nextInt(max) % (max - min + 1) + min));
    }

}
