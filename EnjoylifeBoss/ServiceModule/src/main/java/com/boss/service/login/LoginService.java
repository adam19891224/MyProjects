package com.boss.service.login;

import com.boss.foundation.entity.UserInfo;
import com.boss.foundation.form.LoginForm;

/**
 * ranmin-zhouyuhong
 * 2016/11/30
 */
public interface LoginService {

    UserInfo selectUserInfoByForm(LoginForm form);

}
