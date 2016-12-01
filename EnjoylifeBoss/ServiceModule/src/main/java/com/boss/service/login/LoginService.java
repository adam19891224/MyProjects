package com.boss.service.login;

import com.boss.dao.user.pojo.Users;
import com.boss.foundation.form.LoginForm;

/**
 * ranmin-zhouyuhong
 * 2016/11/30
 */
public interface LoginService {

    Users selectUserInfoByForm(LoginForm form);

}
