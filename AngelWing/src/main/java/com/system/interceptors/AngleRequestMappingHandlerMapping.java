package com.system.interceptors;

import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * ranmin-zhouyuhong
 * 2016/12/20
 */
public class AngleRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    public void setUseSuffixPatternMatch(boolean useSuffixPatternMatch) {

        super.setUseSuffixPatternMatch(false);
    }

    @Override
    public void setUseTrailingSlashMatch(boolean useTrailingSlashMatch) {
        super.setUseTrailingSlashMatch(true);
    }
}
