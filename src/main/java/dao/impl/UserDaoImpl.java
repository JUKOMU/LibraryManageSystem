package dao.impl;

import dao.UserDao;
import domain.User;
import utils.DataSourceUtils;

public class UserDaoImpl extends DataSourceUtils implements UserDao {
    @Override
    public User selectUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = '" + username + " AND password = " + password;
        return executeQueryOne(sql,User.class);
    }
}
