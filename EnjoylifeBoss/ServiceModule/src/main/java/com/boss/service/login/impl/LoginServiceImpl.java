package com.boss.service.login.impl;

import com.boss.dao.user.mapper.UsersMapper;
import com.boss.foundation.entity.UserInfo;
import com.boss.foundation.form.LoginForm;
import com.boss.service.login.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ranmin-zhouyuhong
 * 2016/11/30
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public UserInfo selectUserInfoByForm(LoginForm form) {
        return usersMapper.selectUserInfoByForm(form);
    }
}
