package dao.impl;

import dao.UserDao;
import domain.User;
import utils.DataSourceUtils;

public class UserDaoImpl extends DataSourceUtils implements UserDao {
    @Override
    public User selectUserByUsernameAndPassword(String username, String password) {
        return null;
    }
}
