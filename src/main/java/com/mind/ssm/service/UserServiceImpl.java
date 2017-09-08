package com.mind.ssm.service;

import com.mind.ssm.mapper.UserMapper;
import com.mind.ssm.pojo.User;
import com.mind.ssm.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    //User接口
    @Autowired
    private UserMapper userMapper;

    public List<User> findUser() throws Exception {
        //调用mapper类中的selectByExample方法，如果传入类型为null，则表示无条件查找
        List<User> users = userMapper.selectByExample(null);

        return users;

    }

    public User findByUserName(String userName) throws Exception {

        return userMapper.selectByPrimaryKey(userName);
    }

    public List<User> findByUserNameAndPassword(UserExample example) throws Exception {
        return userMapper.selectByExample(example);
    }

    public int registerUser(User user) throws Exception {
        return userMapper.insert(user);
    }
}
