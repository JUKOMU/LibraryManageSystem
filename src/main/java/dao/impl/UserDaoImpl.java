package dao.impl;

import dao.UserDao;
import domain.User;
import utils.DataSourceUtil;

public class UserDaoImpl extends DataSourceUtil implements UserDao {
    @Override
    public User selectUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM users WHERE user_name = '" + username + "' AND password = '" + password +"'";
        return executeQueryOne(sql,User.class);
    }
}
