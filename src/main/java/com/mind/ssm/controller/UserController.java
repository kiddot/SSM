package com.mind.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mind.ssm.pojo.User;
import com.mind.ssm.pojo.UserExample;
import com.mind.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    //service类
    @Autowired
    private UserService userService;

    /**
     * 查找所用用户控制器方法
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUser")
    public ModelAndView findUser()throws Exception{
        ModelAndView modelAndView = new ModelAndView();

        //调用service方法得到用户列表
        List<User> users = userService.findUser();
        //将得到的用户列表内容添加到ModelAndView中
        modelAndView.addObject("users",users);
        //设置响应的jsp视图
        modelAndView.setViewName("findUser");

        return modelAndView;
    }

    @RequestMapping("/login")
    public @ResponseBody String login(HttpServletRequest request)throws Exception{
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UserExample example = new UserExample();
        example.setDistinct(false);
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        criteria.andPasswordEqualTo(password);

        List<User> users = userService.findByUserNameAndPassword(example);

        if (users.size() != 0){

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", "200");
            jsonObject.put("message", "login success");
            jsonObject.put("data", users.get(0));
            return JSON.toJSONString(jsonObject);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", "400");
            jsonObject.put("message", "login fail");
            jsonObject.put("data", users);
            return JSON.toJSONString(jsonObject);
        }
    }

    @RequestMapping("/register")
    public @ResponseBody String register(HttpServletRequest request)throws Exception{
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");

        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        user.setSex(sex);


        int code = userService.registerUser(user);
        System.out.println(code);

        return code+"";
    }

}
