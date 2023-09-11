package dao;

import domain.User;

/**
 * 登录用户接口
 */
public interface UserDao {
    /**
     * 登录方法，用户名密码
     */
    User selectUserByUsernameAndPassword(String username, String password);
}


