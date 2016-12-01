package com.boss.service.login.impl;

import com.boss.dao.user.pojo.Users;
import com.boss.foundation.form.LoginForm;
import com.boss.service.login.LoginService;
import org.springframework.stereotype.Service;

/**
 * ranmin-zhouyuhong
 * 2016/11/30
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public Users selectUserInfoByForm(LoginForm form) {
        return null;
    }
}
