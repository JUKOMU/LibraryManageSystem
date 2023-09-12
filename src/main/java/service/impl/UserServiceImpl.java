package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;
import utils.Result;

public class UserServiceImpl implements UserService {
    //定义dao对象，调用dao中查询方法
    private final UserDao userDao=new UserDaoImpl();
    @Override
    public Result login(User user) {
        User user1=userDao.selectUserByUsernameAndPassword(user.getUser_name(),user.getPassword());
        System.out.println(user.getUser_name()+" "+user1.getPassword());
        //产生result对象
        Result result=new Result();
        //判断一下user1 是否为空
        if(user1!=null) {
            //保存数据到result
            result.setData(user1);
            //返回
        }else {
            result.setCode(-1);
        }
        return result;
    }
}
