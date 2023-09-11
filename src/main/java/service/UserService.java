package service;

import domain.User;
import utils.Result;

import javax.security.auth.login.LoginException;

public interface UserService {
    public Result login(User user) throws LoginException;
}
