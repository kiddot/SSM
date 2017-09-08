package com.mind.ssm.service;

import com.mind.ssm.pojo.User;
import com.mind.ssm.pojo.UserExample;

import java.util.List;

public interface UserService {
    /**
     * 查找所有用户
     * @return
     * @throws Exception
     */
    List<User> findUser() throws Exception;

    User findByUserName(String userName) throws Exception;

    List<User> findByUserNameAndPassword(UserExample example) throws Exception;

    int registerUser(User user) throws Exception;
}
