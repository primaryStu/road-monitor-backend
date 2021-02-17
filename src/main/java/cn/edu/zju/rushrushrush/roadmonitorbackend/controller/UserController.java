package cn.edu.zju.rushrushrush.roadmonitorbackend.controller;


import cn.edu.zju.rushrushrush.roadmonitorbackend.dao.UserDao;
import cn.edu.zju.rushrushrush.roadmonitorbackend.pojo.UserPojo;
import cn.edu.zju.rushrushrush.roadmonitorbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.annotation.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") Integer id,
                          HttpSession session,
                          @RequestParam(defaultValue="1")Integer page,
                          @RequestParam(defaultValue="10")Integer rows,
                          Model model) {
        UserPojo user = (UserPojo) session.getAttribute("USER_SESSION");

        if(user == null || !id.equals(user.getUserId())) {
            user = new UserPojo();
            user.setUserId(id);
            user = userService.findUserByRegisterId(user);
        }

        if(user == null) {
            model.addAttribute("msg", "can't find user");
            return "error";
        }

        model.addAttribute("user", user);

        return "user";
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @ResponseBody
//    public String testHello(HttpSession session, Model model) {
//        return "test";
//    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public UserPojo register(@RequestBody UserPojo user, HttpSession session) {
        if("admin".equals(user.getRegisterId())) {
            return null;
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setRegisterTime(timestamp);
        user.setLastLoginTime(timestamp);

        user = userService.registerUser(user);

        if(user != null) {
            session.setAttribute("USER_SESSION", user);
        }


        return user;
    }

    // delete user
//    @RequestMapping(value = "/user", method = RequestMethod.DELETE)

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public UserPojo updateUser(@PathVariable("id") Integer id,@RequestBody UserPojo user, HttpSession session) {

        user.setUserId(id);

        if("".equals(user.getPassword())) {
            user.setPassword(null);
        }
        if("".equals(user.getDescribe())) {
            user.setDescribe(null);
        }
        if("".equals(user.getAvatar())) {
            user.setAvatar(null);
        }
        if("".equals(user.getUserName())) {
            user.setUserName(null);
        }
        if("".equals(user.getGender())) {
            user.setGender(null);
        }

        System.out.println(user);

        user = userService.updateUser(user);
        session.setAttribute("USER_SESSION", user);

        return user;
    }



    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @ResponseBody
    public String enableUser(@RequestBody UserPojo user) {
        Boolean success = userService.enableUser(user.getUserId());

        if(success) {
            return "success";
        } else {
            return "fail";
        }

    }

    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @ResponseBody
    public String disableUser(@RequestBody UserPojo user) {

//        System.out.println("-----------------------------" + user.getUserId());
        Boolean success = userService.disableUser(user.getUserId());

        if(success) {
            return "success";
        } else {
            return "fail";
        }
    }

}